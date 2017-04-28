package com.example.dprtenjaca.carservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dprtenjaca.carservices.R;

public class UserChangeDataActivity extends UserBaseActivity {

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

        getSupportActionBar().setTitle("Change account data");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame_user);
        getLayoutInflater().inflate(R.layout.activity_user_change_data, contentFrameLayout);

        editTextFirstName = (EditText) findViewById(R.id.editTextUserChangeDataFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextUserChangeDataLastName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserChangeDataUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextUserChangeDataPassword);
        editTextAddress = (EditText) findViewById(R.id.editTextUserChangeDataAddress);
        editTextEmail = (EditText) findViewById(R.id.editTextUserChangeDataEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextUserChangeDataPhone);

        buttonSubmit = (Button) findViewById(R.id.buttonUserChangeDataSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
