package com.kanfeer.contentproviderapplication.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kanfeer.contentproviderapplication.R;
import com.kanfeer.contentproviderapplication.dao.WorkerBpo;
import com.kanfeer.contentproviderapplication.entity.Worker;
import com.kanfeer.contentproviderapplication.helper.CPSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CPFirstActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,String>> cps;

    private EditText editTextTall;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextId;
    private Button buttonInsert;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_first);

        listView = findViewById(R.id.listView_query);
        editTextTall = findViewById(R.id.editText_tall);
        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextId = findViewById(R.id.editText_id);
        buttonInsert = findViewById(R.id.button_insert);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_update);
        buttonQuery  = findViewById(R.id.button_query);

        CPSQLiteOpenHelper cpsqlOpenHelper = new CPSQLiteOpenHelper(getApplicationContext(),"worker.db",null,1);
        SQLiteDatabase db = cpsqlOpenHelper.getReadableDatabase();
        String sql = "select * from worker";
        Cursor cs = db.rawQuery(sql,null);
        cps = new ArrayList<>();
        while(cs.moveToNext()){
            Map<String,String> map = new HashMap();
            map.put("id",cs.getString(0));
            map.put("name",cs.getString(1));
            map.put("age",cs.getString(2));
            map.put("tall",cs.getString(3));
            cps.add(map);
        }
        simpleAdapter = new SimpleAdapter(getApplicationContext(),cps,R.layout.layout_cps_items,new String[]{"id","name","age","tall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
        listView.setAdapter(simpleAdapter);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Worker p = new Worker();
                p.setId(Integer.parseInt(editTextId.getText().toString()));
                p.setName(editTextName.getText().toString());
                p.setAge(Integer.parseInt(editTextAge.getText().toString()));
                p.setTall(Integer.parseInt(editTextTall.getText().toString()));
                WorkerBpo.insert(getApplicationContext(),p);
                cps = WorkerBpo.queryAllMap(getApplicationContext());
                simpleAdapter = new SimpleAdapter(getApplicationContext(),cps,R.layout.layout_cps_items,new String[]{"id","name","age","tall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
                listView.setAdapter(simpleAdapter);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerBpo.deleteByName(getApplicationContext(),editTextName.getText().toString());
                cps = WorkerBpo.queryAllMap(getApplicationContext());
                simpleAdapter = new SimpleAdapter(getApplicationContext(),cps,R.layout.layout_cps_items,new String[]{"id","name","age","tall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
                listView.setAdapter(simpleAdapter);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editTextId.getText().toString());
                Worker newWorker = new Worker();
                newWorker.setName(editTextName.getText().toString());
                newWorker.setTall(Integer.parseInt(editTextTall.getText().toString()));
                newWorker.setAge(Integer.parseInt(editTextAge.getText().toString()));
                newWorker.setId(Integer.parseInt(editTextId.getText().toString()));
                WorkerBpo.update(getApplicationContext(),newWorker,id);
                cps = WorkerBpo.queryAllMap(getApplicationContext());
                simpleAdapter = new SimpleAdapter(getApplicationContext(),cps,R.layout.layout_cps_items,new String[]{"id","name","age","tall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
                listView.setAdapter(simpleAdapter);
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cps = WorkerBpo.queryAllMap(getApplicationContext());
                simpleAdapter = new SimpleAdapter(getApplicationContext(),cps,R.layout.layout_cps_items,new String[]{"id","name","age","tall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
                listView.setAdapter(simpleAdapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView workerId = view.findViewById(R.id.textView_id);
                TextView workerName = view.findViewById(R.id.textView_name);
                TextView workerAge = view.findViewById(R.id.textView_age);
                TextView workerTall = view.findViewById(R.id.textView_tall);

                editTextId.setText(workerId.getText().toString());
                editTextName.setText(workerName.getText().toString());
                editTextAge.setText(workerAge.getText().toString());
                editTextTall.setText(workerTall.getText().toString());
            }
        });
    }
}