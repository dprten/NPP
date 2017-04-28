package com.example.dprtenjaca.carservices;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters.CarServiceAdapter;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.decoration.DividerItemDecoration;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.ClickListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.RecyclerTouchListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarService;

import java.util.ArrayList;
import java.util.List;

public class UserShoppingCartActivity extends UserBaseActivity {

    private List<CarService> carServiceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarServiceAdapter carServiceAdapter;

    private Paint p = new Paint();

    private Button buttonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Shopping cart");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_user);
        getLayoutInflater().inflate(R.layout.activity_user_shopping_chart, contentFrameLayout);

        buttonOrder = (Button) findViewById(R.id.buttonUserShoppingChartSubmit);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user_shopping_cart);

        carServiceAdapter = new CarServiceAdapter(carServiceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(carServiceAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CarService carService = carServiceList.get(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserShoppingCartActivity.this, "Order placed", Toast.LENGTH_SHORT).show();
            }
        });

        prepareUsersData();
    }

    private void prepareUsersData() {

        CarService carService = new CarService("Service 1", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 2", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 3", 400.00);
        carServiceList.add(carService);

        carService = new CarService("Service 4", 300.00);
        carServiceList.add(carService);

        carService = new CarService("Service 5", 700.00);
        carServiceList.add(carService);

        carServiceAdapter.notifyDataSetChanged();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserShoppingCartActivity.this);
                    builder.setMessage("Are you sure you want to remove this service form shopping cart?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    carServiceAdapter.removeItem(position);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    carServiceAdapter.notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if(direction == ItemTouchHelper.ANIMATION_TYPE_SWIPE_CANCEL) {
                    carServiceAdapter.notifyDataSetChanged();
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
