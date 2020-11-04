package com.kanfeer.kanfeerchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    //protected TextView userPrivacyProtocol;
    private EditText registerPhoneEditText;
    private EditText registerNameEditText;
    private EditText registerPasswordEditText;
    private CheckBox registerProtocolConf;
    private EditText registerConfigCodeEditText;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private Button rewrite;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        userPrivacyProtocol = findViewById(R.id.user_privacy_protocol);
//        userPrivacyProtocol.setAutoLinkMask(123);
        registerPhoneEditText = findViewById(R.id.register_phone_editText);
        registerNameEditText = findViewById(R.id.register_name_editText);
        registerPasswordEditText = findViewById(R.id.register_password_editText);
        registerProtocolConf = findViewById(R.id.register_protocol_conf);
        registerConfigCodeEditText = findViewById(R.id.register_configCode_editText);
        rewrite=findViewById(R.id.rewrite);
        register = findViewById(R.id.put_in);
        sp=getSharedPreferences("ksp",MODE_PRIVATE);
        spe = sp.edit();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!registerNameEditText.getText().toString().equals("")){
                    if (!registerPasswordEditText.getText().toString().equals("")){
                        spe.putString("name",registerNameEditText.getText().toString());
                        spe.putString("password",registerPasswordEditText.getText().toString());
                        Intent it = new Intent(RegisterActivity.this,LoginActivity.class);
                        it.putExtra("name",registerNameEditText.getText().toString());
                        it.putExtra("password",registerPasswordEditText.getText().toString());
                        startActivity(it);
                    }else {
                        Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入账号",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPhoneEditText.setText("");
                registerNameEditText.setText("");
                registerPasswordEditText.setText("");
                registerProtocolConf.setText("");
                registerConfigCodeEditText.setText("");
            }
        });

    }

}