package com.kanfeer.sqliteexamapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kanfeer.sqliteexamapplication.R;
import com.kanfeer.sqliteexamapplication.dao.StudentBpo;
import com.kanfeer.sqliteexamapplication.entity.Student;

import java.util.List;

public class SQLiteMainActivity extends AppCompatActivity {


    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextMajor;

    private Button buttonInsert;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonQuery;

    private ListView listView;
    private Adapter stuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite_main);

        editTextName=findViewById(R.id.student_name_edit_text);
        editTextID=findViewById(R.id.student_id_edit_text);
        editTextAge=findViewById(R.id.student_age_edit_text);
        editTextMajor=findViewById(R.id.student_major_edit_text);

        buttonInsert=findViewById(R.id.button_sql_insert);
        buttonDelete=findViewById(R.id.button_sql_delete);
        buttonQuery=findViewById(R.id.button_sql_query);
        buttonUpdate=findViewById(R.id.button_sql_update);

        listView=findViewById(R.id.list_view_query);
        stuAdapter= Cont

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextID.getText().toString().equals("")) {
                    int id = Integer.parseInt(editTextID.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(),"必须输入学生的Id",Toast.LENGTH_SHORT).show();
                    return;
                }
                String name=editTextName.getText().toString();
                int age=Integer.parseInt(editTextAge.getText().toString());
                String major=editTextMajor.getText().toString();

                Student stu = new Student(id,name,age,major);
                StudentBpo.insert(getApplicationContext(),stu);
            }
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = StudentBpo.queryAllStudent(getApplicationContext());

            }
        });
    }
}