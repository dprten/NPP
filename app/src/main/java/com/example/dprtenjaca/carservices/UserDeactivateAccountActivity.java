package com.example.dprtenjaca.carservices;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class UserDeactivateAccountActivity extends UserBaseActivity {

    Button buttonYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Deactivate account");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_user);
        getLayoutInflater().inflate(R.layout.activity_user_deactivate_account, contentFrameLayout);

        buttonYes = (Button) findViewById(R.id.buttonUserDeactivateYes);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDeactivateAccountActivity.this.finishAffinity();
            }
        });
    }
}
