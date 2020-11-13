package com.kanfeer.fileiospandsdapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOActivity extends AppCompatActivity {


    private EditText fileName;
    private EditText fileContent;
    private Button storeFileButton;
    private Button readFileButton;
    private TextView showFileContent;
    private StringBuffer readString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_i_o);

        fileName=findViewById(R.id.file_name_edit_text);
        fileContent=findViewById(R.id.file_content_edit_text);
        storeFileButton =findViewById(R.id.store_to_sd);
        readFileButton =findViewById(R.id.read_from_sd);
        showFileContent=findViewById(R.id.show_text_from_sd);

        try {
            FileOutputStream fosPrivate = getApplicationContext().openFileOutput("testPrivate.txt", Context.MODE_PRIVATE);
            FileOutputStream fosAppend = getApplicationContext().openFileOutput("testAppend.txt",Context.MODE_APPEND);
            FileOutputStream fosSelf = getApplicationContext().openFileOutput(fileName.getText().toString(),Context.MODE_PRIVATE);
            
            String PrivateString = "I'm Private!";
            byte[] bp =PrivateString.getBytes();
            fosPrivate.write(bp);
            fosPrivate.close();
            Toast.makeText(getApplicationContext(),"Private 成功",Toast.LENGTH_LONG).show();

            String AppendString = "I'm Append!";
            byte[] ba = AppendString.getBytes();
            fosAppend.write(ba);
            fosAppend.close();
            System.out.println("Append 成功");

            storeFileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fromEditText = fileContent.getText().toString();
                    byte[] be = fromEditText.getBytes();
                    try {
                        fosSelf.write(be);
                        fosSelf.close();
                        Toast.makeText(getApplicationContext(),"写入成功",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


            readFileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        FileInputStream fis = getApplicationContext().openFileInput(fileName.getText().toString());
                        readString = new StringBuffer("");
                        byte[] temp = new byte[1024];
                        int len = 0;
                        while((len = fis.read(temp))>0){
                            readString.append(new String(temp,0,len));
                        }
                        fis.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }
                    showFileContent.setText(readString.toString());
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}