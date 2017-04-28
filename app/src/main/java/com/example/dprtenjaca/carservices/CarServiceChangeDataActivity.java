package com.example.dprtenjaca.carservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CarServiceChangeDataActivity extends CarServiceBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Change data");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_car_service);
        getLayoutInflater().inflate(R.layout.activity_car_service_change_data, contentFrameLayout);
    }
}
