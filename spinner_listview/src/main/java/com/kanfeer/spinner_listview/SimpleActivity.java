package com.kanfeer.spinner_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleActivity extends AppCompatActivity {

    private ListView mysls;
    private TextView mytextview;
    //private List maplist;
    private SimpleAdapter mysimpleadapter;

    private String[] names = {"水浒传","三国演义","红楼梦","西游记"};
    private String[] desc = {"shz1","sgyy2","hlm3","xyj4"};
    private int[] imageids = {R.mipmap.shz,R.mipmap.sgyy,R.mipmap.hlm,R.mipmap.xyj};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        mysls = findViewById(R.id.simple_mylist);
        mytextview = findViewById(R.id.simple_mytextview);

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        for (int i=0; i<names.length; i++){
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("lname",names[i]);
            listItem.put("ldesc",desc[i]);
            listItem.put("limg",imageids[i]);
            listItems.add(listItem);
        }

        mysimpleadapter = new SimpleAdapter(this,listItems,R.layout.simple_adapter_item_activity,new String[]{"lname","ldesc","limg"},new int[]{R.id.four_name,R.id.four_desc,R.id.four_IMG});

        mysls.setAdapter(mysimpleadapter);
        mysls.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvname = view.findViewById(R.id.four_name);
                TextView tvdesc = view.findViewById(R.id.four_desc);
                mytextview.setText("请选择：："+tvname.getText()+tvdesc.getText());
            }
        });

    }
}