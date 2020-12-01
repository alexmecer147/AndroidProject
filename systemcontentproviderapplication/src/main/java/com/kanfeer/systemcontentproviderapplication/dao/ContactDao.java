package com.kanfeer.systemcontentproviderapplication.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.kanfeer.systemcontentproviderapplication.entity.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDao {
    private static ContentResolver cr;

    public static void insertToCONTACT(Context context, Contact contact) {
        cr = context.getContentResolver();
        ContentValues cv = new ContentValues();
        Uri uri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, cv);//得到
        Toast.makeText(context,"RawContacts‘ uri:"+(uri),Toast.LENGTH_LONG).show();
        long rawContactId = ContentUris.parseId(uri);
        //插入data表
        //插入姓名
        cv.clear();
        cv.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);//插入raw哪行
        cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        cv.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, contact.getName());
        cr.insert(ContactsContract.Data.CONTENT_URI, cv);
        //插入电话
        cv.clear();
        cv.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getPhone());
        cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        cr.insert(ContactsContract.Data.CONTENT_URI, cv);
        //插入电子邮箱
        cv.clear();
        cv.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);//由于插入失败而的到一个可以用的id
        cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);//数据类型是email
        cv.put(ContactsContract.CommonDataKinds.Email.DATA, contact.getEmail());
        cv.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        cr.insert(ContactsContract.Data.CONTENT_URI, cv);
        //所以插入了三遍，姓名，电话，电子邮箱都对应着三条记录，但是他们的raw_contact_id是一样的，是同一个用户
    }

    public static void deleteFromCONTACTByName(Context context, String name) {
        ContentResolver cr = context.getContentResolver();
        //Uri uri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").build();
        int rawContactId;
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        //得到那个要操作的ID通过名字
        Cursor cs = cr.query(uri, new String[]{ContactsContract.Data._ID}, "display_name = ?", new String[]{name}, null);
        //cr.delete(ContactsContract.Data.CONTENT_URI, "raw_contact_id = ?",new String[]{});

        if (cs.moveToFirst()) {
            int id = cs.getInt(0);//得到要删除的ID了
            cr.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            cr.delete(uri, "raw_contact_id=?", new String[]{id + ""});
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        }
        cs.close();
    }

    public static void updateOnCONTACT(Context context, String oldName, Contact contact) {
        ContentResolver cr = context.getContentResolver();
        //Uri uri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").build();
        //int rawContactId;
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cs = cr.query(uri, new String[]{ContactsContract.Data._ID}, "display_name = ?", new String[]{oldName}, null);
        if (cs.moveToFirst()) {
            int id = cs.getInt(0);//得到要删除的ID了
            ContentValues cv = new ContentValues();
            cv.clear();
            cv.put("display_name", contact.getName());
            cr.update(uri, cv, "_id = ?", new String[]{"" + id});
            uri = Uri.parse("content://com.android.contacts/data");
            //修改姓名
            cv.clear();
            cv.put(ContactsContract.Data.RAW_CONTACT_ID, id);
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            cv.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, contact.getName());
            if (cr.update(uri, cv, "mimetype_id=? and raw_contact_id=?", new String[]{"7", id + ""})<=0){
                cr.insert(ContactsContract.Data.CONTENT_URI, cv);
            }
            //修改电话
            cv.clear();
            cv.put(ContactsContract.Data.RAW_CONTACT_ID, id);
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getPhone());
            cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            if (cr.update(uri, cv, "mimetype_id=? and raw_contact_id=?", new String[]{"5", id + ""})<=0){
                cr.insert(ContactsContract.Data.CONTENT_URI, cv);
            }
            //修改电子邮箱
            cv.clear();
            cv.put(ContactsContract.Data.RAW_CONTACT_ID, id);
            cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);//数据类型是email
            cv.put(ContactsContract.CommonDataKinds.Email.DATA, contact.getEmail());
            cv.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
            if (cr.update(uri, cv, "mimetype_id=? and raw_contact_id=?", new String[]{"1", id + ""})<=0){
                cr.insert(ContactsContract.Data.CONTENT_URI, cv);
            }
            Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
        }
        cs.close();
    }

    public static List<Map<String, String>> query(Context context){
        List<Map<String,String>> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cs1 = cr.query(uri, new String[]{ContactsContract.Data._ID}, null, null, null);
        uri = Uri.parse("content://com.android.contacts/data");
        while (cs1.moveToNext()){
            int id = cs1.getInt(0);
            System.out.println("id:"+id);
            Map<String, String> map = new HashMap<>();
            Cursor cs2 = cr.query(uri, new String[]{"data1"}, "mimetype_id=? and raw_contact_id=?", new String[]{"7", id + ""}, null);
            if (cs2.moveToPosition(0)) {
                //System.out.println("cs2.getColumnName:" + cs2.getColumnName(0) + "; cs2.getString" + cs2.getString(0));
                map.put("name", cs2.getString(0));
            }else {
                Toast.makeText(context,"获取姓名失败",Toast.LENGTH_SHORT).show();
                map.put("name","空姓名");
            }
            Cursor cs3 = cr.query(uri, new String[]{"data1"}, "mimetype_id=? and raw_contact_id=?", new String[]{"5", id + ""}, null);
            if (cs3.moveToPosition(0)) {
                System.out.println("cs3.isNUll" + cs3.isNull(1));
                map.put("phone", cs3.getString(0));
            }else {
                Toast.makeText(context,"获取电话失败",Toast.LENGTH_SHORT).show();
                map.put("phone","空电话");
            }
            Cursor cs4 = cr.query(uri, new String[]{"data1"}, "mimetype_id=? and raw_contact_id=?", new String[]{"1", id + ""}, null);
            if(cs4.moveToPosition(0)) {
                System.out.println("cs4.getColumnName" + cs4.getColumnName(0));
                map.put("email", cs4.getString(0));
            }else {
                Toast.makeText(context,"获取邮箱失败",Toast.LENGTH_SHORT).show();
                map.put("email", "空邮箱");
            }
            list.add(map);
        }
        return list;
    }
}



