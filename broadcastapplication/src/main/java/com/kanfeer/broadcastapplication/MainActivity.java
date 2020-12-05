package com.kanfeer.broadcastapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText editTextBroadcast;
    private Button buttonSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextBroadcast=findViewById(R.id.editText_broadcast);
        buttonSendBroadcast=findViewById(R.id.button_sendBroadcast);
        buttonSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.kanfeer.action.sendmsg");
//                intent.setAction();
                intent.putExtra("msg",editTextBroadcast.getText().toString());
                intent.setComponent(new ComponentName("com.kanfeer.broadcastapplication","com.kanfeer.broadcastapplication.TheFirstReceiver"));
                //System.out.println(editTextBroadcast.getText().toString()+"8888");
                Log.i(TAG,editTextBroadcast.getText().toString());
                sendOrderedBroadcast(intent,null);
            }
        });
    }
}