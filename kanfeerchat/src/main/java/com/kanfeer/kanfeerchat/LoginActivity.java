package com.kanfeer.kanfeerchat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private CheckBox password_box;
    private CheckBox account_box;
    private Button login_button;
    private ImageView setting_img;
    private TextView kcname;
    private TextView kcpassword;
    private TextView kceditpassword;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("ksp",MODE_PRIVATE);
        spe = sp.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        kcname=findViewById(R.id.kf_name);
        kcpassword=findViewById(R.id.kf_password);

        password_box = findViewById(R.id.ckbox_password);
        account_box = findViewById(R.id.ckbox_account);
        account_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!kcname.getText().toString().equals("")&&isChecked) {
                    spe.putString("name", kcname.getText().toString());
                    spe.apply();
                }
            }
        });
        password_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!kcpassword.getText().toString().equals("")&&isChecked) {
                    spe.putString("password", kcpassword.getText().toString());
                    spe.apply();
                }
            }
        });

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kcname.getText().toString().equals(sp.getString("name","123"))||kcname.getText().toString().equals("123")) {
                    if (kcpassword.getText().toString().equals(sp.getString("password","123"))||kcpassword.getText().toString().equals("123")) {
                        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                        kcname.setText(sp.getString("name","123"));
                        kcpassword.setText(sp.getString("password","123"));
                        Bundle bd = new Bundle();
                        bd.putString("iname",kcname.getText().toString());
                        bd.putString("ipassword",kcpassword.getText().toString());
                        intent.putExtras(bd);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_LONG).show();
                        kcpassword.setText("");
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "账号错误",Toast.LENGTH_LONG).show();
                    kcname.setText("");
                }
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
