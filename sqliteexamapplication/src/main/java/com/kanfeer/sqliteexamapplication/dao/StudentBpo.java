package com.kanfeer.sqliteexamapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.kanfeer.sqliteexamapplication.entity.Student;
import com.kanfeer.sqliteexamapplication.helper.StudentSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentBpo {

    public static List<Map<String,String>> queryAllMap(Context context){
        StudentSQLiteOpenHelper ssop = new StudentSQLiteOpenHelper(context,"student.db",null,1);
        SQLiteDatabase db = ssop.getReadableDatabase();
        String sql = "select id,name,age,major from student";
        Cursor cs = db.rawQuery(sql,null);
        List<Map<String,String>> list = new ArrayList<>();
        while (cs.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put("id",cs.getString(0));
            map.put("name",cs.getString(1));
            map.put("age",cs.getString(2));
            map.put("major",cs.getString(3));
            list.add(map);
        }
        cs.close();
        db.close();
        return list;
    }

    public static LinkedList<Student> queryAllStudent(Context context){
        StudentSQLiteOpenHelper ssop = new StudentSQLiteOpenHelper(context,"student.db",null,1);
        SQLiteDatabase db = ssop.getReadableDatabase();
        String sql = "select id,name,age,major from student";
        Cursor cs = db.rawQuery(sql,null);
        LinkedList<Student> students = new LinkedList<>();
        while (cs.moveToNext()){
            Student p = new Student();
            p.setStudentId(cs.getInt(0));
            p.setStudentAge(cs.getInt(2));
            p.setStudentName(cs.getString(1));
            p.setStudentMajor(cs.getString(3));
            students.add(p);
        }
        cs.close();
        db.close();
        return students;
    }

    public static void insert(Context context,Student student){
        StudentSQLiteOpenHelper studentSQLiteOpenHelper = new StudentSQLiteOpenHelper(context,"student.db",null,1);
        SQLiteDatabase stuDB = studentSQLiteOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",student.getStudentId());
        cv.put("name",student.getStudentName());
        cv.put("major",student.getStudentMajor());
        cv.put("age",student.getStudentAge());
        long position = stuDB.insert("student",null,cv);
        //String sqlInsert = "insert into student (id,name,age,major) values (?,?,?,?)";
        //stuDB.execSQL(sqlInsert,new Object[]{student.getStudentId(),student.getStudentName(),student.getStudentAge(),student.getStudentMajor()});
        stuDB.close();
    }

    public static void deleteById(Context context, int studentId){
        StudentSQLiteOpenHelper ssop = new StudentSQLiteOpenHelper(context,"student.db",null,1);
        SQLiteDatabase sqlDB = ssop.getWritableDatabase();
       // String sqlDelete = "delete from student where id = ?";
        sqlDB.delete("student","id=?",new String[]{String.valueOf(studentId)});
        //sqlDB.execSQL(sqlDelete,new Object[]{studentId});
        sqlDB.close();
        Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
    }

    public static void update(Context context,Student student, int oldId){
        StudentSQLiteOpenHelper ssop = new StudentSQLiteOpenHelper(context,"student.db",null,1);
        SQLiteDatabase sqlDB = ssop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",student.getStudentId());
        cv.put("name",student.getStudentName());
        cv.put("age",student.getStudentAge());
        cv.put("major",student.getStudentMajor());
        sqlDB.update("student",cv,"id=? ",new String[]{String.valueOf(oldId)});
        sqlDB.close();
    }
}
