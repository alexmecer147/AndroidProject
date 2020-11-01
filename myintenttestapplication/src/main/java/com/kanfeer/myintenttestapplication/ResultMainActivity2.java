package com.kanfeer.myintenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultMainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_main2);

        TextView nameText = findViewById(R.id.register_result_name);
        TextView genderText = findViewById(R.id.register_result_gender);
        TextView passwordText = findViewById(R.id.register_result_password);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Person p = (Person) bd.getSerializable("person");
        nameText.setText("f3"+p.getName());
        genderText.setText(p.getGender());
        passwordText.setText(p.getPassword());

        Intent reIntent = new Intent();
        reIntent.putExtra("twoToMain","来自Result2的返回");
        setResult(2,reIntent);
        finish();
        startActivity(reIntent);
    }
}