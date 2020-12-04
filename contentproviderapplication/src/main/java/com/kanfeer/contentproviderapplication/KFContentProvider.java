package com.kanfeer.contentproviderapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kanfeer.contentproviderapplication.helper.CPSQLiteOpenHelper;

public class KFContentProvider extends ContentProvider {

    private static CPSQLiteOpenHelper cpsoh;
    private static UriMatcher uriMatcher;
    private static final String AUTHORITY = "com.kanfeer.contentproviderapplication.KFContentProvider";

    @Override
    public boolean onCreate() {
        cpsoh = new CPSQLiteOpenHelper(this.getContext(),"worker.db",null,1);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"workerall",1);
        uriMatcher.addURI(AUTHORITY,"workers/*",2);//*任何字符
        uriMatcher.addURI(AUTHORITY,"worker/#",3);//#任何文字
        uriMatcher.addURI(AUTHORITY,"insertworker",1);
        uriMatcher.addURI(AUTHORITY,"deleteworker/#",1);
        uriMatcher.addURI(AUTHORITY,"deleteallworker",2);
        uriMatcher.addURI(AUTHORITY,"updateworker",1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cs = null;
        SQLiteDatabase db = cpsoh.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 1:
            case 2:
            case 3:
                cs = db.query("worker", projection, selection, selectionArgs, "", "",sortOrder);
            default:
                System.out.println("没有合适的uri");
        }
        return cs;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = cpsoh.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case 1:
                db.insert("worker",null,values);
                db.close();
                break;
            default:
                throw new IllegalArgumentException("未知的URI");
        }
        db.close();
        this.getContext().getContentResolver().notifyChange(Uri.parse("content://"+AUTHORITY+"/workerall"),null);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int t = -1;
        SQLiteDatabase db = cpsoh.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case 1:
                t = db.delete("worker",selection,selectionArgs);
                break;
            case 2:
                t = db.delete("worker",null,null);
                break;
            default:
                throw new IllegalArgumentException("未知的uri");
        }
        db.close();
        this.getContext().getContentResolver().notifyChange(Uri.parse("content://"+AUTHORITY+"/workerall"),null);
        return t;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int t = -1;
        SQLiteDatabase db = cpsoh.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case 1:
                t = db.update("worker",values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("未知的uri");
        }
        db.close();
        this.getContext().getContentResolver().notifyChange(Uri.parse("content://"+AUTHORITY+"/worker"),null);
        return t;
    }
}
