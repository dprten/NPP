package com.example.dprtenjaca.carservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CarServiceDeactivateAccountActivity extends CarServiceBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Deactivate account");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_car_service);
        getLayoutInflater().inflate(R.layout.activity_car_service_deactivate_account, contentFrameLayout);
    }
}
