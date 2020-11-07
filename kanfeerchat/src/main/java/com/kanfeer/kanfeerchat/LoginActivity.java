package com.kanfeer.kanfeerchat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
    private EditText kcname;
    private EditText kcpassword;
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
        kceditpassword=findViewById(R.id.edit_password_text);
        kceditpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this,EditPassword.class);
                it.putExtra("opassword",sp.getString("password",""));
                it.putExtra("oname",sp.getString("name",""));
                if (kcname.getText().toString().equals(sp.getString("name", ""))) {
                    Toast.makeText(getApplicationContext(),sp.getString("name","nooo"),Toast.LENGTH_SHORT).show();
                    startActivity(it);
                }else {
                    Toast.makeText(getBaseContext(),"不存在账号"+kcname.getText().toString()+",请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (sp.getString("isKeepName","").equals("true")){
            kcname.setText(sp.getString("keepName",""));
        }
        if (sp.getString("isKeepPassword","").equals("true")){
            kcpassword.setText(sp.getString("keepPassword",""));
        }

        password_box = findViewById(R.id.ckbox_password);
        account_box = findViewById(R.id.ckbox_account);
        account_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked&&sp.getString("name","").equals(kcname.getText().toString())) {
                    spe.putString("keepName", kcname.getText().toString());
                    spe.putString("isKeepName","true");
                    spe.apply();
                    account_box.setChecked(true);
                    //kcname.setText(sp.getString("name",""));
                }else {
                    spe.putString("isKeepName","false");
                }
            }
        });
        password_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked&&sp.getString(sp.getString("name",""),"").equals(kcpassword.getText().toString())) {
                    spe.putString("keepPassword", kcpassword.getText().toString());
                    spe.putString("isKeepPassword","true");
                    spe.apply();
                    account_box.setChecked(true);
                }else {
                    spe.putString("isKeepPassword","false");
                }
            }
        });


        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kcname.getText().toString().equals(sp.getString("name",""))) {
                    if (sp.getString(sp.getString("name",""),"").equals(kcpassword.getText().toString())) {
                        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                        kcname.setText(sp.getString("name","123"));
                        kcpassword.setText(sp.getString("password","123"));
                        Bundle bd = new Bundle();
                        bd.putString("iname",kcname.getText().toString());
                        bd.putString("ipassword",kcpassword.getText().toString());
                        intent.putExtras(bd);
                        //Intent intent1 = getIntent();

                        startActivity(intent);

                    }else {
                        Toast.makeText(getApplicationContext(),"密码错误::"+sp.getString(kcname.getText().toString(),""),Toast.LENGTH_LONG).show();
                        kcpassword.setText("");
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "账号错误",Toast.LENGTH_LONG).show();
                    kcname.setText("");
                    kcpassword.setText("");
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
