package com.kanfeer.sqliteexamapplication.teacher.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TeacherSQLiteOpenHelper extends SQLiteOpenHelper {

    public TeacherSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table teacher(id integer primary key,name varchar(20),gender varchar(20),salary integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUpgrade = "alter table teacher add address varchar(20)";
        if (newVersion == 2 && oldVersion == 1){
            db.execSQL(sqlUpgrade);
        }
    }
}
