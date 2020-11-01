package com.kanfeer.myintenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.BlockingDeque;


public class RegisterResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        TextView nameText = findViewById(R.id.register_result_name);
        TextView genderText = findViewById(R.id.register_result_gender);
        TextView passwordText = findViewById(R.id.register_result_password);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Person p = (Person) bd.getSerializable("person");
        nameText.setText("f1"+p.getName());
        genderText.setText(p.getGender());
        passwordText.setText(p.getPassword());

//        nameText.setText(intent.getStringExtra("name"));
//        genderText.setText(intent.getStringExtra("gender"));
//        passwordText.setText(intent.getStringExtra("password"));

    }
}