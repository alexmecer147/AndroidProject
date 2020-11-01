package com.kanfeer.kanfeerchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    //protected TextView userPrivacyProtocol;
    private EditText registerPhoneEditText;
    private EditText registerNameEditText;
    private EditText registerPasswordEditText;
    private CheckBox registerProtocolConf;
    private EditText registerConfigCodeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        userPrivacyProtocol = findViewById(R.id.user_privacy_protocol);
//        userPrivacyProtocol.setAutoLinkMask(123);



    }
    public void clearTheForm() {
        registerPhoneEditText = findViewById(R.id.register_phone_editText);
        registerNameEditText = findViewById(R.id.register_name_editText);
        registerPasswordEditText = findViewById(R.id.register_password_editText);
        registerProtocolConf = findViewById(R.id.register_protocol_conf);
        registerConfigCodeEditText = findViewById(R.id.register_configCode_editText);
        registerPhoneEditText.setText("");
        registerNameEditText.setText("");
        registerPasswordEditText.setText("");
        registerProtocolConf.setText("");
        registerConfigCodeEditText.setText("");
        
    }
}