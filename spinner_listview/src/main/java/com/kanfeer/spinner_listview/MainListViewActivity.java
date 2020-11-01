package com.kanfeer.spinner_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainListViewActivity extends AppCompatActivity {

    private ListView bookListView;
    public ArrayAdapter blvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        bookListView = findViewById(R.id.books_list_view);
        String [] strings = {"摆渡人","不要相信任何人","高等数学","厚黑学"};
        String[] strings1 = getResources().getStringArray(R.array.kkk);
        blvAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,strings1);
        bookListView.setAdapter(blvAdapter);
    }
}