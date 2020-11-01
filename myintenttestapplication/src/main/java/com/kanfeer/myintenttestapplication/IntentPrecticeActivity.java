package com.kanfeer.myintenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.Serializable;

public class IntentPrecticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_prectice);

        Button button = findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.register_name);
                EditText password = findViewById(R.id.register_password);
                RadioButton male = findViewById(R.id.male);
                String gender = male.isChecked() ? "男" : "女";
                Person p = new Person(name.getText().toString(),
                        password.getText().toString(),gender);
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", p);
                //Intent intent = new Intent(IntentPrecticeActivity.this,RegisterResultActivity.class);
                Intent intent = new Intent(IntentPrecticeActivity.this, ResultMainActivity1.class);

                intent.putExtras(bundle);

//                Intent intent = new Intent(IntentPrecticeActivity.this,RegisterResultActivity.class);
//                String textName = name.getText().toString();
//                String textPassword = password.getText().toString();
//                intent.putExtra("name",textName);
//                intent.putExtra("password",textPassword);
//                intent.putExtra("gender",gender);

                startActivityForResult(intent,1);

            }
        });

        onActivityResult(1,1,);
    }
}

class Person implements Serializable{
    private String name;
    private String password;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person() {
    }

    public Person(String name, String password, String gender) {
        this.name = name;
        this.password = password;
        this.gender = gender;
    }
}