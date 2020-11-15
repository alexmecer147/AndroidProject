package com.kanfeer.sqliteexamapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kanfeer.sqliteexamapplication.R;
import com.kanfeer.sqliteexamapplication.dao.StudentBpo;
import com.kanfeer.sqliteexamapplication.entity.Student;

import java.util.List;
import java.util.Map;

public class SQLiteMainActivity extends AppCompatActivity {

    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextMajor;

    private Button buttonInsert;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonQuery;

    private View view;
    private ListView listView;
    private TextView numbers;
    private SimpleAdapter stuAdapter;
    private int oldid=0;
    private List<Map<String,String>> students;
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

        view= LayoutInflater.from(this).inflate(R.layout.layout_listview_header,null);
        listView=findViewById(R.id.list_view_query);
        numbers=findViewById(R.id.textView_student_number);
        listView.addHeaderView(view);

        //stuAdapter= new SimpleAdapter(getApplicationContext(),null,R.layout.layout_query_item,new String[]{"sid","sname","sage","smajor"},new int[]{R.id.textView_student_id,R.id.textView_student_name,R.id.textView_student_age,R.id.textView_student_major});
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_query_item,parent,false);
                TextView sid = view.findViewById(R.id.textView_student_id);
                TextView sname = view.findViewById(R.id.textView_student_name);
                TextView sage = view.findViewById(R.id.textView_student_age);
                TextView smajor = view.findViewById(R.id.textView_student_major);

                oldid=Integer.parseInt(sid.getText().toString());
                editTextID.setText(sid.getText());
                editTextName.setText(sname.getText());
                editTextAge.setText(sage.getText());
                editTextMajor.setText(smajor.getText());
                //oldid = Integer.parseInt(editTextID.getText().toString());
                Toast.makeText(getApplicationContext(),"点击了第"+position+"个",Toast.LENGTH_SHORT).show();
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                int age;
                if (!editTextID.getText().toString().equals("")) {
                    id = Integer.parseInt(editTextID.getText().toString());
                    String name=editTextName.getText().toString();
                    age = Integer.parseInt(editTextAge.getText().toString());
                    String major=editTextMajor.getText().toString();

                    Student stu = new Student(id,name,age,major);
                    StudentBpo.insert(getApplicationContext(),stu);
                    students = StudentBpo.queryAllMap(getApplicationContext());
//                    if (stuAdapter==null){
                        stuAdapter = new SimpleAdapter(getApplicationContext(),students,R.layout.layout_query_item,new String[]{"id","name","age","major"}, new int[]{R.id.textView_student_id,R.id.textView_student_name,R.id.textView_student_age,R.id.textView_student_major});
                        listView.setAdapter(stuAdapter);
//                        //numbers.setText("长度是"+students.size());
//                    }else {
//                        stuAdapter.notifyDataSetChanged();
//                    }
                }else {
                    Toast.makeText(getApplicationContext(),"必须输入学生的Id",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stuAdapter!=null){
                    int deleteid = Integer.parseInt(editTextID.getText().toString());
                    StudentBpo.deleteById(getApplicationContext(),deleteid);
                    students = StudentBpo.queryAllMap(getApplicationContext());
                    //if (stuAdapter==null){
                        stuAdapter = new SimpleAdapter(getApplicationContext(),students,R.layout.layout_query_item,new String[]{"id","name","age","major"}, new int[]{R.id.textView_student_id,R.id.textView_student_name,R.id.textView_student_age,R.id.textView_student_major});
                        listView.setAdapter(stuAdapter);
                        //numbers.setText("长度是"+students.size());
                    //}else {
                      //  stuAdapter.notifyDataSetChanged();
                    //}
                }
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //oldid = Integer.parseInt(editTextID.getText().toString());
                Student newstu = new Student();
                newstu.setStudentId(Integer.parseInt(editTextID.getText().toString()));
                newstu.setStudentName(editTextName.getText().toString());
                newstu.setStudentAge(Integer.parseInt(editTextAge.getText().toString()));
                newstu.setStudentMajor(editTextMajor.getText().toString());
                StudentBpo.update(getApplicationContext(),newstu,oldid);
                //List<Map<String,String>> students = StudentBpo.queryAllMap(getApplicationContext());
                //stuAdapter.notifyDataSetChanged();
                students = StudentBpo.queryAllMap(getApplicationContext());
//                if (stuAdapter==null){
                    stuAdapter = new SimpleAdapter(getApplicationContext(),students,R.layout.layout_query_item,new String[]{"id","name","age","major"}, new int[]{R.id.textView_student_id,R.id.textView_student_name,R.id.textView_student_age,R.id.textView_student_major});
                    listView.setAdapter(stuAdapter);
//                    //numbers.setText("长度是"+students.size());
//                }else {
//                    stuAdapter.notifyDataSetChanged();
//                }
            }
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students = StudentBpo.queryAllMap(getApplicationContext());
//                if (stuAdapter==null){
                    stuAdapter = new SimpleAdapter(getApplicationContext(),students,R.layout.layout_query_item,new String[]{"id","name","age","major"}, new int[]{R.id.textView_student_id,R.id.textView_student_name,R.id.textView_student_age,R.id.textView_student_major});
                    listView.setAdapter(stuAdapter);
//                    //numbers.setText("长度是"+students.size());
//                }else {
//                    stuAdapter.notifyDataSetChanged();
//                }
            }
        });
    }
}