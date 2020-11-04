package com.kanfeer.thesharedpreferencesapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPreferencesMainActivity extends AppCompatActivity {


    private TextView keyTextView;
    private TextView valueTextView;
    private Button readButton;
    private Button writeButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_main);

        sharedPreferences = getSharedPreferences("thesp", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        keyTextView=findViewById(R.id.shared_preferences_key_tv);
        valueTextView=findViewById(R.id.shared_preferences_value_tv);
        readButton = findViewById(R.id.shared_preferences_read_button);
        writeButton = findViewById(R.id.shared_preferences_write_button);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = sharedPreferences.getString("time","0");
                int random = sharedPreferences.getInt("random",666);
                keyTextView.setText("time");
                valueTextView.setText(random+" ");
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"+"hh:mm:ss");
                editor.putString("time",sdf.format(new Date()));
                editor.putInt("random",(int) (Math.random()*100));
                editor.apply();
            }
        });

    }
}