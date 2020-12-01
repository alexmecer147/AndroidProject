package com.kanfeer.contentproviderapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.kanfeer.contentproviderapplication.entity.Worker;
import com.kanfeer.contentproviderapplication.helper.CPSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WorkerBpo {

    public static List<Map<String,String>> queryAllMap(Context context){
        CPSQLiteOpenHelper ssop = new CPSQLiteOpenHelper(context,"worker.db",null,1);
        SQLiteDatabase db = ssop.getReadableDatabase();
        String sql = "select * from worker";
        Cursor cs = db.rawQuery(sql,null);
        List<Map<String,String>> list = new ArrayList<>();
        while (cs.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put("id",cs.getString(0));
            map.put("name",cs.getString(1));
            map.put("age",cs.getString(2));
            map.put("tall",cs.getString(3));
            list.add(map);
        }
        cs.close();
        db.close();
        return list;
    }

    public static LinkedList<Worker> queryAllWorker(Context context){
        CPSQLiteOpenHelper ssop = new CPSQLiteOpenHelper(context,"worker.db",null,1);
        SQLiteDatabase db = ssop.getReadableDatabase();
        String sql = "select * from worker";
        Cursor cs = db.rawQuery(sql,null);
        LinkedList<Worker> workers = new LinkedList<>();
        while (cs.moveToNext()){
            Worker p = new Worker();
            p.setId(cs.getInt(0));
            p.setAge(cs.getInt(2));
            p.setName(cs.getString(1));
            p.setTall(cs.getInt(3));
            workers.add(p);
        }
        cs.close();
        db.close();
        return workers;
    }

    public static void insert(Context context,Worker worker){
        CPSQLiteOpenHelper studentSQLiteOpenHelper = new CPSQLiteOpenHelper(context,"worker.db",null,1);
        SQLiteDatabase stuDB = studentSQLiteOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("workerId",worker.getId());
        cv.put("workerName",worker.getName());
        cv.put("workerTall",worker.getTall());
        cv.put("workerAge",worker.getAge());
        long position = stuDB.insert("worker",null,cv);
        //String sqlInsert = "insert into student (id,name,age,major) values (?,?,?,?)";
        //stuDB.execSQL(sqlInsert,new Object[]{student.getStudentId(),student.getStudentName(),student.getStudentAge(),student.getStudentMajor()});
        stuDB.close();
    }

    public static void deleteByName(Context context, String name){
        CPSQLiteOpenHelper ssop = new CPSQLiteOpenHelper(context,"worker.db",null,1);
        SQLiteDatabase sqlDB = ssop.getWritableDatabase();
        // String sqlDelete = "delete from student where id = ?";
        sqlDB.delete("worker","workerName=?",new String[]{String.valueOf(name)});
        //sqlDB.execSQL(sqlDelete,new Object[]{studentId});
        sqlDB.close();
        Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
    }

    public static void update(Context context,Worker worker, int oldId){
        CPSQLiteOpenHelper ssop = new CPSQLiteOpenHelper(context,"worker.db",null,1);
        SQLiteDatabase sqlDB = ssop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("workerId",worker.getId());
        cv.put("workerName",worker.getName());
        cv.put("workerAge",worker.getAge());
        cv.put("workerTall",worker.getTall());
        sqlDB.update("worker",cv,"workerId=? ",new String[]{String.valueOf(oldId)});
        sqlDB.close();
    }
}
