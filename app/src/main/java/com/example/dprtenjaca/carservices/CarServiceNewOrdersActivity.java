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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters.CarServiceAdapter;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.decoration.DividerItemDecoration;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.ClickListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.RecyclerTouchListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dprtenjaca on 10.11.2016..
 */

public class CarServiceNewOrdersActivity extends CarServiceBaseActivity {

    private List<CarService> carServiceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarServiceAdapter carServiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("New orders");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_car_service);
        getLayoutInflater().inflate(R.layout.activity_car_service_new_orders, contentFrameLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_car_services_new_order);

        carServiceAdapter = new CarServiceAdapter(carServiceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(carServiceAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                CarService carService = carServiceList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(CarServiceNewOrdersActivity.this);
                builder.setMessage("Do you want to accept order: " + carService.getDescription() + "?")
                        .setCancelable(true)
                        .setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getBaseContext(), "ACCEPTED", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("DECLINE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                carServiceAdapter.removeItem(position);
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareUsersData();
    }

    private void prepareUsersData() {

        CarService carService = new CarService("Service 1", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 2", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 2", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 3", 500.00);
        carServiceList.add(carService);

        carService = new CarService("Service 4", 700.00);
        carServiceList.add(carService);

        carServiceAdapter.notifyDataSetChanged();
    }
}
