package com.kanfeer.fileiospandsdapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {


    private EditText keyText;
    private EditText storeContent;
    private TextView showContent;
    private Button storeButton;
    private Button showButton;

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        keyText=findViewById(R.id.the_key_you_make);
        storeContent=findViewById(R.id.shared_store_edit_text);
        showContent=findViewById(R.id.shared_show_text_view);
        storeButton=findViewById(R.id.store_text_to_xml_button);
        showButton=findViewById(R.id.show_text_from_xml_button);

        sp=getSharedPreferences("rosp",MODE_PRIVATE);
        spe=sp.edit();

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!keyText.getText().toString().equals("")){
                    if (!storeContent.getText().toString().equals("")){
                        spe.putString(keyText.getText().toString(),storeContent.getText().toString());
                        spe.apply();
                        Toast.makeText(getApplicationContext(),"已经存入，键-"+keyText.getText().toString()+"数值-"+storeContent.getText().toString(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"请输入键值对应的文字数据",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入键值",Toast.LENGTH_LONG).show();
                }
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!keyText.getText().toString().equals("")){
                    showContent.setText(sp.getString(keyText.getText().toString(),""));
                }else {
                    Toast.makeText(getApplicationContext(),"请输入你要显示文字的键值",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}