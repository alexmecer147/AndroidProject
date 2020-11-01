package com.kanfeer.myintenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultMainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_main1);

        TextView nameText = findViewById(R.id.register_result_name);
        TextView genderText = findViewById(R.id.register_result_gender);
        TextView passwordText = findViewById(R.id.register_result_password);
        Button but1 = findViewById(R.id.return_main_button1);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Person p = (Person) bd.getSerializable("person");
        nameText.setText("f2"+p.getName());
        genderText.setText(p.getGender());
        passwordText.setText(p.getPassword());

        Intent reIntent = new Intent();
        reIntent.putExtra("oneToMain","来自Result1的返回");
        setResult(1,reIntent);
        finish();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(reIntent);
            }
        });

    }
}