package com.example.dprtenjaca.carservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class AdminUserAddActivity extends AdminBaseActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Add User");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_admin_user_add, contentFrameLayout);

        editTextFirstName = (EditText) findViewById(R.id.editTextAdminUserAddFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextAdminUserAddLastName);
        editTextUsername = (EditText) findViewById(R.id.editTextAdminUserAddUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextAdminUserAddPassword);
        editTextAddress = (EditText) findViewById(R.id.editTextAdminUserAddAddress);
        editTextEmail = (EditText) findViewById(R.id.editTextAdminUserAddEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextAdminUserAddPhone);

        buttonSubmit = (Button) findViewById(R.id.buttonAdminUserAddSubmit);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setToolbarBack();
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
