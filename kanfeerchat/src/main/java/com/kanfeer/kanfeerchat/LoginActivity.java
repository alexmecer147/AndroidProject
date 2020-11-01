package com.kanfeer.kanfeerchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    private CheckBox password_box;
    private CheckBox account_box;
    private Button login_button;
    private ImageView setting_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password_box = findViewById(R.id.ckbox_password);
        account_box = findViewById(R.id.ckbox_account);
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });
        setting_img=findViewById(R.id.setting_imageView);
        setting_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
