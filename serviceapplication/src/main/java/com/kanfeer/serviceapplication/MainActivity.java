package com.kanfeer.serviceapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText accumulation,multiplication;
    private Button start,stop,bdo;
    private TextView result;
    AMService.AMBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accumulation=findViewById(R.id.editText_sum);
        multiplication=findViewById(R.id.editText_mul);
        start=findViewById(R.id.button_start_service);
        stop=findViewById(R.id.button_stop_service);
        bdo=findViewById(R.id.button_do);
        result=findViewById(R.id.textView_show_result);
        AMService amService = new AMService();
        AMServiceConnection amconn = new AMServiceConnection();
        bdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!accumulation.getText().toString().equals("")){
                    int a = Integer.parseInt(accumulation.getText().toString());
                    a = amService.accumulate(a);
                    result.setText(a+"");
                }else if (accumulation.getText().toString().equals("")&&!multiplication.getText().toString().equals("")){
                    int m = Integer.parseInt(multiplication.getText().toString());
                    m = binder.multiplicate(m);
                    result.setText(m+"");
                }else {
                    Toast.makeText(MainActivity.this, "请输入一个数后再计算", Toast.LENGTH_SHORT).show();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setPackage("com.kanfeer.serviceapplication");
                bindService(intent,amconn,BIND_AUTO_CREATE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(amconn);
            }
        });

    }
    class AMServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("Service Connected");
            binder = (AMService.AMBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("Service not Connected");
        }
    }

}