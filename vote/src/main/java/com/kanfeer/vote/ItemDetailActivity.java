package com.kanfeer.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        //LinearLayout layoutView = findViewById(R.id.project_layout);
        TextView projectCount = findViewById(R.id.project_count);
        TextView projectName = findViewById(R.id.project_name);
        ImageView projectPhoto = findViewById(R.id.project_photo);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        VoteActivity.Object vo = (VoteActivity.Object) bundle.getSerializable("ps");
//        projectCount.setText(vo.getCount());
//        projectName.setText(vo.getName());

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        projectCount.setText(bundle.getInt("count",0)+"");
        projectName.setText(bundle.getString("name"));
        switch (bundle.getInt("position")){
            case 0:
                projectPhoto.setImageResource(R.mipmap.project_photo1);
                break;
            case 1:
                projectPhoto.setImageResource(R.mipmap.project_photo2);
                break;
            case 2:
                projectPhoto.setImageResource(R.mipmap.project_photo3);
                break;
            case 3:
                projectPhoto.setImageResource(R.mipmap.project_photo4);
                break;
            case 4:
                projectPhoto.setImageResource(R.mipmap.project_photo5);
                break;

        }

//        Toast.makeText(this,bundle.getInt("count",0)+"",Toast.LENGTH_LONG).show();


//        Intent intent = getIntent();
//        Bundle bd = intent.getExtras();
//        Person p = (Person) bd.getSerializable("person");
//        nameText.setText("f1"+p.getName());
//        genderText.setText(p.getGender());
//        passwordText.setText(p.getPassword());

//        layoutView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ItemDetailActivity.this,VoteActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}