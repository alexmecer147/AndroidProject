package com.kanfeer.sqliteexamapplication.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentSQLiteOpenHelper extends SQLiteOpenHelper {

    public StudentSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//直接执行建表语句
        String sqlCreateTable = "create table student(id integer primary key,name varchar(20),age integer,major varchar(20))";
        db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlInsertColumn = "alter table student add scores integer";
        if (newVersion == 2&& oldVersion==1){
            db.execSQL(sqlInsertColumn);
        }
    }
}
