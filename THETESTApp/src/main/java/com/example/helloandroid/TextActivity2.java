package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TextActivity2 extends AppCompatActivity {

    TextView tv=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2);
        tv=findViewById(R.id.textView);
        tv.setText("Java nb!!!");
    }
}