package com.kanfeer.spinner_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.security.PublicKey;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

public class mainspinner extends AppCompatActivity {

    private Spinner spinnerColor;
    private Spinner cityspinner;
    public ArrayAdapter sadapter;
    public ArrayAdapter cadapter;
    private List<String> cList;
    public Spinner fspinner;
    public ArrayAdapter<CharSequence> fadapter;
    public EditText ed;
    public Spinner soc;
    public Spinner fc;
    public ArrayAdapter socadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainspinner);
        String [][] cityson = {{"日照1","日照2","日照333"},{"北京好","好","挺好"},{"济南还好","一般一般"},{"iibsiv","..coj"},{"12","34","45","67"}};
        String []arr1 = getResources().getStringArray(R.array.city_label);

        soc=findViewById(R.id.son_of_city_spinner);
        fc=findViewById(R.id.father_city);
        socadapter=ArrayAdapter.createFromResource(this,R.array.city_label,R.layout.activity_main_list_view);
        fc.setAdapter(socadapter);
        soc.setPrompt("看看城市");
        soc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerColor=super.findViewById(R.id.color);
        spinnerColor.setPrompt("选择一个颜色");
        sadapter = ArrayAdapter.createFromResource(this,R.array.color_adapter,android.R.layout.simple_spinner_item);
        //sadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerColor.setAdapter(sadapter);


        cityspinner=findViewById(R.id.city_spinner);
        cityspinner.setPrompt("aaaaa");
        cadapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arr1);
        cityspinner.setAdapter(cadapter);


        fspinner=findViewById(R.id.food_spinner);
        fadapter = ArrayAdapter.createFromResource(this,R.array.food_adapter,R.layout.support_simple_spinner_dropdown_item);
        fspinner.setAdapter(fadapter);

        ed=findViewById(R.id.spinner_deit_text);

        this.fspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                mainspinner.this.ed.setText(value);
            }
            public void onNothingSelected(AdapterView<?> pa){}

        });




    }


    String []arr = {"JAVA11","Androiwdad","xawcaml","ioawdawdawds"};
//    public List<String> getcList() {
//        for (int i = 0; i < arr.length; i++) {
//            cList.add(arr[i]);
//        }
//        return cList;
//    }

}

