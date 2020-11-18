package com.kanfeer.sqliteexamapplication.teacher.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kanfeer.sqliteexamapplication.R;
import com.kanfeer.sqliteexamapplication.teacher.dao.TeacherBpo;
import com.kanfeer.sqliteexamapplication.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherSQLiteActivity extends AppCompatActivity {

    private EditText editTextTeacherId;
    private EditText editTextTeacherName;
    private EditText editTextTeacherGender;
    private EditText editTextTeacherSalary;

    private Button buttonTInsert;
    private Button buttonTDelete;
    private Button buttonTUpdate;
    private Button buttonTQuery;

    private ListView listViewTeachers;
    private List<Map<String,String>> teachers;
    private SimpleAdapter teacherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_s_q_lite);

        editTextTeacherId = findViewById(R.id.edittext_teacher_id);
        editTextTeacherName = findViewById(R.id.edittext_teacher_name);
        editTextTeacherGender = findViewById(R.id.edittext_teacher_gender);
        editTextTeacherSalary= findViewById(R.id.edittext_teacher_salary);

        buttonTInsert = findViewById(R.id.button_teacher_insert);
        buttonTDelete = findViewById(R.id.button_teacher_delete);
        buttonTUpdate = findViewById(R.id.button_teacher_update);
        buttonTQuery = findViewById(R.id.button_teacher_query);

        listViewTeachers=findViewById(R.id.listview_teachers);
        teachers = new ArrayList<>();
        View tHeader = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_listview_theader,null);
        listViewTeachers.addHeaderView(tHeader);

        teachers = TeacherBpo.queryMaps(getApplicationContext());
        teacherAdapter = new SimpleAdapter(getApplicationContext(),teachers,R.layout.teacherslistitem,new String[]{"id","name","gender","salary"},new int[]{R.id.list_item_id,R.id.list_item_name,R.id.list_item_gender,R.id.list_item_salary});
        listViewTeachers.setAdapter(teacherAdapter);

        listViewTeachers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editTextTeacherId.setText(teachers.get(position-1).get("id"));
                editTextTeacherName.setText(teachers.get(position-1).get("name"));
                editTextTeacherGender.setText(teachers.get(position-1).get("gender"));
                editTextTeacherSalary.setText(teachers.get(position-1).get("salary"));
            }
        });

        buttonTInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Teacher t = new Teacher();
                if (!editTextTeacherId.getText().toString().equals("")) {
                    t.setId(Integer.parseInt(editTextTeacherId.getText().toString()));
                }else {
                    Toast.makeText(getApplicationContext(),"请插入正确的教师号",Toast.LENGTH_SHORT).show();
                }
                t.setName(editTextTeacherName.getText().toString());
                t.setGender(editTextTeacherGender.getText().toString());
                t.setSalary(Integer.parseInt(editTextTeacherSalary.getText().toString()));
                TeacherBpo.insert(getApplicationContext(),t);
                teachers = TeacherBpo.queryMaps(getApplicationContext());
                teacherAdapter = new SimpleAdapter(getApplicationContext(),teachers,R.layout.teacherslistitem,new String[]{"id","name","gender","salary"},new int[]{R.id.list_item_id,R.id.list_item_name,R.id.list_item_gender,R.id.list_item_salary});
                listViewTeachers.setAdapter(teacherAdapter);
                editTextTeacherId.setText("");
                editTextTeacherName.setText("");
                editTextTeacherGender.setText("");
                editTextTeacherSalary.setText("");
            }
        });
        buttonTDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int d;
                d = Integer.parseInt(editTextTeacherId.getText().toString());
                TeacherBpo.deleteById(getApplicationContext(),d);
                teachers = TeacherBpo.queryMaps(getApplicationContext());
                teacherAdapter = new SimpleAdapter(getApplicationContext(),teachers,R.layout.teacherslistitem,new String[]{"id","name","gender","salary"},new int[]{R.id.list_item_id,R.id.list_item_name,R.id.list_item_gender,R.id.list_item_salary});
                listViewTeachers.setAdapter(teacherAdapter);
                editTextTeacherId.setText("");
                editTextTeacherName.setText("");
                editTextTeacherGender.setText("");
                editTextTeacherSalary.setText("");
            }
        });

        buttonTUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher t = new Teacher();
                t.setId(Integer.parseInt(editTextTeacherId.getText().toString()));
                t.setName(editTextTeacherName.getText().toString());
                t.setGender(editTextTeacherGender.getText().toString());
                t.setSalary(Integer.parseInt(editTextTeacherSalary.getText().toString()));
                TeacherBpo.update(getApplicationContext(),t,Integer.parseInt(editTextTeacherId.getText().toString()));
                teachers = TeacherBpo.queryMaps(getApplicationContext());
                teacherAdapter = new SimpleAdapter(getApplicationContext(),teachers,R.layout.teacherslistitem,new String[]{"id","name","gender","salary"},new int[]{R.id.list_item_id,R.id.list_item_name,R.id.list_item_gender,R.id.list_item_salary});
                listViewTeachers.setAdapter(teacherAdapter);
                editTextTeacherId.setText("");
                editTextTeacherName.setText("");
                editTextTeacherGender.setText("");
                editTextTeacherSalary.setText("");
            }
        });

        buttonTQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teachers = TeacherBpo.queryMaps(getApplicationContext());
                teacherAdapter = new SimpleAdapter(getApplicationContext(),teachers,R.layout.teacherslistitem,new String[]{"id","name","gender","salary"},new int[]{R.id.list_item_id,R.id.list_item_name,R.id.list_item_gender,R.id.list_item_salary});
                listViewTeachers.setAdapter(teacherAdapter);
            }
        });


    }
}