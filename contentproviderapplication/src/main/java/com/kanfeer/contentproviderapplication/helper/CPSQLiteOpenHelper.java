package com.kanfeer.contentproviderapplication.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CPSQLiteOpenHelper extends SQLiteOpenHelper {
    public CPSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table cptable (id integer primary key AUTOINCREMENT,name varchar(20) not null,age integer not null)";
        String sql2 = "create table worker (workerId integer primary key AUTOINCREMENT,workerName varchar(20) not null,workerAge integer not null,workerTall integer not null)";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            System.out.println("更新数据库版本成功，当前版本为"+newVersion);
            String sqlup = "alter table cp add 新字段"+newVersion+" text ";
            db.execSQL(sqlup);
        }
    }
}
