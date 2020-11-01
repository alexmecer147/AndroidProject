package com.kanfeer.kanfeerchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EditPassword extends AppCompatActivity {

    private ImageView rewrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        rewrite = findViewById(R.id.rewrite_old_password_button);
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPassword.this,LoginActivity.class);
            }
        });
    }
}