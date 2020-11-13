package com.kanfeer.kanfeerchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditPassword extends AppCompatActivity {

    private ImageView rewrite;
    private EditText editOldPassword;
    private EditText editPassword;
    private EditText confirmEditPassword;
    private Button changePassword;
    private ImageView returnImg;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        sp=getSharedPreferences("ksp",MODE_PRIVATE);
        spe=sp.edit();
        Intent it = getIntent();
        final String Oldname = it.getStringExtra("oname");
        final String Oldpassword = it.getStringExtra("opassword");
        editOldPassword=findViewById(R.id.edit_password_editText);
        editPassword=findViewById(R.id.edit_new_password_editText);
        confirmEditPassword=findViewById(R.id.edit_config_password_editText);
        rewrite = findViewById(R.id.rewrite_old_password_button);
        returnImg=findViewById(R.id.edit_password_return_button);
        changePassword=findViewById(R.id.edit_password_button);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editOldPassword.getText().toString().equals("")){
                    if (editOldPassword.getText().toString().equals(Oldpassword)){
                        if (editPassword.getText().toString().equals(confirmEditPassword.getText().toString())){
                            spe.putString("password",editPassword.getText().toString());
                            spe.putString(Oldname,editPassword.getText().toString());
                            Intent intent = new Intent(EditPassword.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"修改成功::"+Oldname+"++"+sp.getString(Oldname,""),Toast.LENGTH_LONG).show();
                            spe.apply();
                        }else {
                            Toast.makeText(getApplicationContext(),"新密码与确认密码不一样",Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"旧密码输入错误",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入旧密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOldPassword.setText("");
            }
        });
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPassword.this,LoginActivity.class);
            }
        });
    }
}