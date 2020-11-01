package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class App2MainActivity extends AppCompatActivity {

    private Button test_button;
    private EditText et;
    private int i  = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app2_main);
        et = findViewById(R.id.edit_text);
        test_button = findViewById(R.id.test_button);
        test_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        et.setText("111");
                    }
                }

        );
    }
}
