package com.kanfeer.systemcontentproviderapplication.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kanfeer.systemcontentproviderapplication.R;
import com.kanfeer.systemcontentproviderapplication.dao.ContactDao;
import com.kanfeer.systemcontentproviderapplication.entity.Contact;

import java.util.List;
import java.util.Map;

public class SystemContentProviderActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;

    private Button buttonInsert;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonQuery;

    private SimpleAdapter sysAdapter;
    private ListView listView;
    private List<Map<String,String>> listContacts;
    private Uri uri = Uri.parse("content://com.android.contacts/data");
    private String oldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_content_provider);
        //uri = Uri.parse("")

        ContentResolver cr = getContentResolver();
        SysObserver sysObserver = new SysObserver(getApplicationContext(),new Handler());
        cr.registerContentObserver(uri,true,sysObserver);

        editTextName=findViewById(R.id.editText_system_name);
        editTextEmail=findViewById(R.id.editText_system_email);
        editTextPhone=findViewById(R.id.editText_system_phone);

        buttonInsert=findViewById(R.id.button_insert);
        buttonDelete=findViewById(R.id.button_delete);
        buttonUpdate=findViewById(R.id.button_update);
        buttonQuery=findViewById(R.id.button_query);

        listView=findViewById(R.id.listView_system);

        listContacts = ContactDao.query(getApplicationContext());
        sysAdapter = new SimpleAdapter(getApplicationContext(),listContacts,R.layout.layout_list_view_system,new String[]{"name","phone","email"},new int[]{R.id.textView_contact_name,R.id.textView_contact_phone,R.id.textView_contact_email});
        listView.setAdapter(sysAdapter);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                contact.setEmail(editTextEmail.getText().toString());
                ContactDao.insertToCONTACT(getApplicationContext(),contact);
                listContacts = ContactDao.query(getApplicationContext());
                sysAdapter = new SimpleAdapter(getApplicationContext(),listContacts,R.layout.layout_list_view_system,new String[]{"name","phone","email"},new int[]{R.id.textView_contact_name,R.id.textView_contact_phone,R.id.textView_contact_email});
                listView.setAdapter(sysAdapter);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String name = editTextName.getText().toString();
                ContactDao.deleteFromCONTACTByName(getApplicationContext(),name);
                listContacts = ContactDao.query(getApplicationContext());
                sysAdapter = new SimpleAdapter(getApplicationContext(),listContacts,R.layout.layout_list_view_system,new String[]{"name","phone","email"},new int[]{R.id.textView_contact_name,R.id.textView_contact_phone,R.id.textView_contact_email});
                listView.setAdapter(sysAdapter);
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact = new Contact();
                newContact.setName(editTextName.getText().toString());
                newContact.setPhone(editTextPhone.getText().toString());
                newContact.setEmail(editTextEmail.getText().toString());
                ContactDao.updateOnCONTACT(getApplicationContext(),oldName,newContact);
                listContacts = ContactDao.query(getApplicationContext());
                sysAdapter = new SimpleAdapter(getApplicationContext(),listContacts,R.layout.layout_list_view_system,new String[]{"name","phone","email"},new int[]{R.id.textView_contact_name,R.id.textView_contact_phone,R.id.textView_contact_email});
                listView.setAdapter(sysAdapter);
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString() == "") {
                    listContacts = ContactDao.query(getApplicationContext());
                }else {
                    listContacts = ContactDao.queryOne(getApplicationContext(),editTextName.getText().toString());
                }
                sysAdapter = new SimpleAdapter(getApplicationContext(),listContacts,R.layout.layout_list_view_system,new String[]{"name","phone","email"},new int[]{R.id.textView_contact_name,R.id.textView_contact_phone,R.id.textView_contact_email});
                listView.setAdapter(sysAdapter);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = view.findViewById(R.id.textView_contact_name);
                TextView phone = view.findViewById(R.id.textView_contact_phone);
                TextView email = view.findViewById(R.id.textView_contact_email);

                editTextName.setText(name.getText().toString());
                editTextPhone.setText(phone.getText().toString());
                editTextEmail.setText(email.getText().toString());
                oldName = name.getText().toString();
            }
        });
    }
}

class SysObserver extends ContentObserver{
    private Context context;
    public SysObserver(Context context,Handler handler){
        super(handler);
        this.context=context;
    }
    public void onChange(boolean selfChange){
        Toast.makeText(context, "调用了SysObserver对象了", Toast.LENGTH_SHORT).show();
    }
}