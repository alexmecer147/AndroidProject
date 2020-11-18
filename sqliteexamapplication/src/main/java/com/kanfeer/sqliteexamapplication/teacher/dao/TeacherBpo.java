package com.kanfeer.sqliteexamapplication.teacher.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kanfeer.sqliteexamapplication.teacher.entity.Teacher;
import com.kanfeer.sqliteexamapplication.teacher.helper.TeacherSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherBpo {

    public static void insert(Context context, Teacher teacher){
        TeacherSQLiteOpenHelper sqLiteOpenHelper = new TeacherSQLiteOpenHelper(context,"teacher.db",null,1);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String sql = "insert into teacher values(?,?,?,?)";
        db.execSQL(sql,new Object[]{teacher.getId(),teacher.getName(),teacher.getGender(),teacher.getSalary()});
        db.close();
    }

    public static void deleteById(Context context, int id){
        TeacherSQLiteOpenHelper sqLiteOpenHelper = new TeacherSQLiteOpenHelper(context,"teacher.db",null,1);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String sql = "delete from teacher where id = ?";
        db.execSQL(sql,new Object[]{id});
        db.close();
    }

    public static void update(Context context, Teacher teacher, int oldid){
        TeacherSQLiteOpenHelper sqLiteOpenHelper = new TeacherSQLiteOpenHelper(context,"teacher.db",null,1);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String sql = "update teacher set name = ? , gender = ? , salary = ? where id = ?";
        db.execSQL(sql,new Object[]{teacher.getName(),teacher.getGender(),teacher.getSalary(),oldid});
        db.close();
    }

    public static List<Map<String,String>> queryMaps(Context context){
        TeacherSQLiteOpenHelper sqLiteOpenHelper = new TeacherSQLiteOpenHelper(context,"teacher.db",null,1);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        String sql = "select * from teacher";
        Cursor cs = db.rawQuery(sql,null);
        List<Map<String,String>> teachers = new ArrayList<>();
        while (cs.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put("id",cs.getString(0));
            map.put("name",cs.getString(1));
            map.put("gender",cs.getString(2));
            map.put("salary",cs.getString(3));
            teachers.add(map);
        }
        return teachers;
    }
}
