package com.example.dprtenjaca.carservices;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dprtenjaca on 10.11.2016..
 */

public class UserCarServicesCompanyActivity extends UserBaseActivity {

    private List<CarServiceCompany> carServiceCompanies = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarServiceCompanyAdapter carServiceCompanyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Companies");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_user);
        getLayoutInflater().inflate(R.layout.activity_user_car_services_company, contentFrameLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user_car_services);

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
                Intent usersIntent = new Intent(getApplicationContext(), UserCarServicesActivity.class);
                usersIntent.putExtra(NAV_MENU_BUNDLE_KEY, 0);
                startActivity(usersIntent);
                //Toast.makeText(getApplicationContext(), carServiceCompany.getName() + " is selected!", Toast.LENGTH_SHORT).show();
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
    }
}
