package com.example.dprtenjaca.carservices;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters.UsersAdapter;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.decoration.DividerItemDecoration;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.ClickListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.RecyclerTouchListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarService;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dprtenjaca on 10.11.2016..
 */

public class AdminUsersActivity extends AdminBaseActivity{

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;

    private Paint p = new Paint();
    private View view;

    private AlertDialog.Builder alertDialog;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private int edit_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Users");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_admin_users, contentFrameLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_admin_user_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersIntent = new Intent(getApplicationContext(), AdminUserAddActivity.class);
                usersIntent.putExtra(NAV_MENU_BUNDLE_KEY, 0);
                startActivity(usersIntent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        usersAdapter = new UsersAdapter(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(usersAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User user = userList.get(position);
                Toast.makeText(getApplicationContext(), user.getUsername() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareUsersData();
    }

    private void prepareUsersData() {

        User user = new User("Mirko", "Mirkic", "mmirkic", "test", "test@test.com", "mmirkic");
        userList.add(user);

        User user1 = new User("Pero", "Peric", "pperic", "test", "test@test.com", "pperic");
        userList.add(user1);

        User user2 = new User("Mirko", "Mirkic", "mmirkic", "test", "test@test.com", "mmirkic");
        userList.add(user);

        User user3 = new User("Mirko", "Mirkic", "mmirkic", "test", "test@test.com", "mmirkic");
        userList.add(user);

        User user4 = new User("Mirko", "Mirkic", "mmirkic", "test", "test@test.com", "mmirkic");
        userList.add(user);

        usersAdapter.notifyDataSetChanged();
        initSwipe();
        initDialog();
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminUsersActivity.this);
                    builder.setMessage("Are you sure you want to delete this user?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    usersAdapter.removeItem(position);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    usersAdapter.notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit User");
                    editTextFirstName.setText(userList.get(position).getFirstName());
                    editTextLastName.setText(userList.get(position).getLastName());
                    editTextUsername.setText(userList.get(position).getUsername());
                    editTextAddress.setText(userList.get(position).getAddress());
                    editTextEmail.setText(userList.get(position).getEmail());
                    editTextPassword.setText(userList.get(position).getPassword());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initDialog(){
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_user_edit,null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = new User(editTextFirstName.getText().toString(),
                        editTextLastName.getText().toString(),
                        editTextUsername.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextPassword.getText().toString());
                userList.set(edit_position, user);
                usersAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        editTextFirstName = (EditText) view.findViewById(R.id.editTextAdminUserFirstName);
        editTextLastName = (EditText) view.findViewById(R.id.editTextAdminUserLastName);
        editTextUsername = (EditText) view.findViewById(R.id.editTextAdminUserUsername);
        editTextPassword = (EditText) view.findViewById(R.id.editTextAdminUserPassword);
        editTextAddress = (EditText) view.findViewById(R.id.editTextAdminUserAddress);
        editTextEmail = (EditText) view.findViewById(R.id.editTextAdminUserEmail);
        editTextPhone = (EditText) view.findViewById(R.id.editTextAdminUserPhone);
    }

    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
