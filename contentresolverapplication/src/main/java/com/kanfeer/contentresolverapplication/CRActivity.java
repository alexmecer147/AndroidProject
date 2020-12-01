package com.kanfeer.contentresolverapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRActivity extends AppCompatActivity {

    private ListView listView;
    private List<Map<String,String>> workers;
    private SimpleAdapter simpleAdapter;
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextTall;
    private Button buttonInsert;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonQuery;
    private Cursor cs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r);
        Uri uri = Uri.parse("content://com.kanfeer.contentproviderapplication.KFContentProvider/workerall");
        //getContentResolver().registerContentObserver(uri,true,new KFContentObserver(new Handler()));
        ContentResolver cr = getApplicationContext().getContentResolver();
        listView = findViewById(R.id.listView_resolver);
        editTextId = findViewById(R.id.editText_id);
        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextTall = findViewById(R.id.editText_tall);
        buttonInsert = findViewById(R.id.button_insert);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_update);
        buttonQuery = findViewById(R.id.button_query);
        workers = new ArrayList<>();
        cs = cr.query(uri,new String[]{"workerId","workerName","workerAge","workerTall"},null,null);
//        initListView();
        try {
            System.out.println(cs.isNull(0)+"99");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("workerId",Integer.parseInt(editTextId.getText().toString()));
                cv.put("workerName",editTextName.getText().toString());
                cv.put("workerAge",Integer.parseInt(editTextAge.getText().toString()));
                cv.put("workerTall",Integer.parseInt(editTextTall.getText().toString()));
                cr.insert(uri,cv);
                initListView();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr.delete(uri,"workerId = ?",new String[]{editTextId.getText().toString()});
                initListView();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("workerId",Integer.parseInt(editTextId.getText().toString()));
                cv.put("workerName",editTextName.getText().toString());
                cv.put("workerAge",Integer.parseInt(editTextAge.getText().toString()));
                cv.put("workerTall",Integer.parseInt(editTextAge.getText().toString()));
                cr.update(uri,cv,"workerId = ?",new String[]{editTextId.getText().toString()});
                initListView();
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initListView();
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
    public void initListView(){
        while (cs.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put("workerId",String.valueOf(cs.getInt(0)));
            map.put("workerName",cs.getString(1));
            map.put("workerAge",String.valueOf(cs.getInt(2)));
            map.put("workerTall",String.valueOf(cs.getInt(3)));
            workers.add(map);
        }
        simpleAdapter = new SimpleAdapter(getApplicationContext(),workers,R.layout.layout_resolver_item,new String[]{"workerId","workerName","workerAge","workerTall"},new int[]{R.id.textView_id,R.id.textView_name,R.id.textView_age,R.id.textView_tall});
        listView.setAdapter(simpleAdapter);
    }
    class KFContentObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public KFContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Toast.makeText(getApplicationContext(),"调用onChange方法了",Toast.LENGTH_SHORT).show();
        }
    }
}