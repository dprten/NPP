package com.example.dprtenjaca.carservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.com.example.dprtenjaca.adapters.CarServiceAdapter;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.decoration.DividerItemDecoration;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.ClickListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.listeners.RecyclerTouchListener;
import com.example.dprtenjaca.carservices.com.example.dprtenjaca.model.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServiceAcceptedOrdersActivity extends CarServiceBaseActivity {

    private List<CarService> carServiceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarServiceAdapter carServiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Accepted orders");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_car_service);
        getLayoutInflater().inflate(R.layout.activity_car_service_accepted_orders, contentFrameLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_car_services_accepted_order);

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
                Toast.makeText(getApplicationContext(), carService.getDescription() + " clicked!", Toast.LENGTH_SHORT).show();
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
