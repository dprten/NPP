package com.example.dprtenjaca.carservices;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters.CarServiceCompanyAdapter;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.decoration.DividerItemDecoration;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.ClickListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.RecyclerTouchListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarServiceCompany;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminCarServicesActivity extends AdminBaseActivity {

    private List<CarServiceCompany> carServiceCompanies = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarServiceCompanyAdapter carServiceCompanyAdapter;

    private Paint p = new Paint();
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Car Services");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_admin_car_services, contentFrameLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_admin_car_services);

        carServiceCompanyAdapter = new CarServiceCompanyAdapter(carServiceCompanies);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(carServiceCompanyAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CarServiceCompany carServiceCompany = carServiceCompanies.get(position);
                Toast.makeText(getApplicationContext(), carServiceCompany.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareUsersData();
    }

    private void prepareUsersData() {

        CarServiceCompany carServiceCompany = new CarServiceCompany("Company 1");
        carServiceCompanies.add(carServiceCompany);

        carServiceCompany = new CarServiceCompany("Company 2");
        carServiceCompanies.add(carServiceCompany);

        carServiceCompany = new CarServiceCompany("Company 3");
        carServiceCompanies.add(carServiceCompany);

        carServiceCompany = new CarServiceCompany("Company 4");
        carServiceCompanies.add(carServiceCompany);

        carServiceCompanyAdapter.notifyDataSetChanged();
        initSwipe();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminCarServicesActivity.this);
                    builder.setMessage("Are you sure you want to delete this company?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    carServiceCompanyAdapter.removeItem(position);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    carServiceCompanyAdapter.notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    carServiceCompanyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX <= 0){
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
}
