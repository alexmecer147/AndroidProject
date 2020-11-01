package com.kanfeer.calculatorapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KCalculatorActivity extends AppCompatActivity {

    private Button btn_c;
    private Button btn_del;
    private Button btn_exp;
    private Button btn_mul;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_sub;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_add;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_equ;
    private Button btn_poi;
    private Button btn_0;
    private TextView textView_result;
    private String str_result = "";
    private double result = 0;
    private double preResult = 0;
    private String sym = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_calculator);

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_add = findViewById(R.id.btn_add);
        btn_c = findViewById(R.id.btn_c);
        btn_del = findViewById(R.id.btn_del);
        btn_equ = findViewById(R.id.btn_equ);
        btn_exp = findViewById(R.id.btn_exp);
        btn_mul = findViewById(R.id.btn_mul);
        btn_poi = findViewById(R.id.btn_poi);
        btn_sub = findViewById(R.id.btn_sub);
        textView_result = findViewById(R.id.result);

        btn_poi.setOnClickListener(v -> {
            str_result += ".";
            textView_result.setText(str_result);
        });
        btn_0.setOnClickListener(v -> {
            str_result += "0";
            textView_result.setText(str_result);
        });

        btn_1.setOnClickListener(v -> {
            str_result += "1";
            textView_result.setText(str_result);
        });
        btn_2.setOnClickListener(v -> {
            str_result += "2";
            textView_result.setText(str_result);
        });
        btn_3.setOnClickListener(v -> {
            str_result += "3";
            textView_result.setText(str_result);
        });
        btn_4.setOnClickListener(v -> {
            str_result += "4";
            textView_result.setText(str_result);
        });
        btn_5.setOnClickListener(v -> {
            str_result += "5";
            textView_result.setText(str_result);
        });
        btn_6.setOnClickListener(v -> {
            str_result += "6";
            textView_result.setText(str_result);
        });
        btn_7.setOnClickListener(v -> {
            str_result += "7";
            textView_result.setText(str_result);
        });
        btn_8.setOnClickListener(v -> {
            str_result += "8";
            textView_result.setText(str_result);
        });

        btn_9.setOnClickListener(v -> {
            str_result += "9";
            textView_result.setText(str_result);
        });

        btn_add.setOnClickListener(v -> {
            if (!str_result.equals("")) {
                if (!Character.isDigit(str_result.charAt(str_result.length() - 1)) && (str_result.charAt(str_result.length() - 1) != '.')) {
                    str_result = str_result.substring(0, str_result.length() - 1);
                } else {
                    preResult = (int)calCore(str_result);
                    if (preResult != 0) {
                        str_result = String.valueOf(preResult);
                    }
                }
                str_result += "+";
                textView_result.setText(str_result);
//                if (preResult!=0){
//                    str_result ="";
//                    str_result +=preResult;
//                }
            }
        });
        btn_sub.setOnClickListener(v -> {
            if (!str_result.equals("")) {
                if (!Character.isDigit(str_result.charAt(str_result.length() - 1)) && (str_result.charAt(str_result.length() - 1) != '.')) {
                    str_result = str_result.substring(0, str_result.length() - 1);
                } else {
                    preResult = (int)calCore(str_result);
                    str_result = String.valueOf(preResult);
                }
                str_result += "-";
                textView_result.setText(str_result);
            }else {
                str_result +="-";
                textView_result.setText(str_result);
            }
        });
        btn_mul.setOnClickListener(v -> {
            if (!str_result.equals("")) {
                if (!Character.isDigit(str_result.charAt(str_result.length() - 1)) && (str_result.charAt(str_result.length() - 1) != '.')) {
                    str_result = str_result.substring(0, str_result.length() - 1);
                } else {
                    preResult = calCore(str_result);
                    if (preResult != 0) {
                        str_result = String.valueOf(preResult);
                    }
                }
                str_result += "*";
                textView_result.setText(str_result);
            }
        });
        btn_exp.setOnClickListener(v -> {
            if (!str_result.equals("")) {
                if (!Character.isDigit(str_result.charAt(str_result.length() - 1)) && (str_result.charAt(str_result.length() - 1) != '.')) {
                    str_result = str_result.substring(0, str_result.length() - 1);
                } else {
                    preResult = calCore(str_result);
                    if (preResult != 0) {
                        str_result = String.valueOf(preResult);
                    }
                }
                str_result += "/";
                textView_result.setText(str_result);
            }
        });
        btn_del.setOnClickListener(v -> {
            if (!str_result.equals("")) {
                str_result = str_result.substring(0, str_result.length() - 1);
                preResult = calCore(str_result);
                textView_result.setText(preResult+"");
            }
        });
        btn_c.setOnClickListener(v -> {
            str_result = "";
            preResult = 0;
            textView_result.setText(str_result);
        });
        btn_equ.setOnClickListener(v ->{
            if (!str_result.equals("")){
                preResult = calCore(str_result);
                str_result = String.valueOf(preResult);
                textView_result.setText(str_result);
            }
        });
    }

    public double calCore(String a) {
        char[] s = a.toCharArray();
        String nt1 = "";
        String nt2 = "";
        int i=0;
        for (; i < s.length; i++) {
            if (Character.isDigit(s[i]) || String.valueOf(s[i]).equals(".")) {
                nt1+=s[i];
            }else if ((String.valueOf(s[i]).equals("-"))&&i==0){
                nt1+=s[i];
            }else {
                sym=String.valueOf(s[i]);
                break;
            }
        }

        for (int p = i + 1; p < s.length; p++) {
            if (Character.isDigit(s[p]) || String.valueOf(s[p]).equals(".")) {
                nt2+=s[p];
            }
        }
        System.out.println("from cal:"+ nt1+" "+sym+" "+nt2);
        switch (sym) {
            case "+":
                if (!nt2.equals("")){
                    System.out.println(str_result);
                    preResult = Double.parseDouble(nt1) + Double.parseDouble(nt2);
                }else if (!nt1.equals("")) {
                    preResult = Double.parseDouble(nt1) + 0.0;
                }else {
                    preResult =  0.0 + Double.parseDouble(nt2);
                }
                System.out.println(preResult+"+");
                return preResult;
//               break;
            case "-":
                if (!nt2.equals("")&&!nt1.equals("")) {
                    System.out.println(str_result);
                    preResult = Double.parseDouble(nt1) - Double.parseDouble(nt2);
                }else if (!nt1.equals("")){
                    preResult = Double.parseDouble(nt1) - 0;
                }else {
                    preResult =  0 - Double.parseDouble(nt2);
                }
                System.out.println(preResult+"-");
                return preResult;
//               break;
            case "*":
                if (!nt2.equals("")) {
                    System.out.println(str_result);
                    preResult = Double.parseDouble(nt1) * Double.parseDouble(nt2);
                }else {
                    preResult = Double.parseDouble(nt1) * 1.0;
                }
                System.out.println(preResult+"*");
                return preResult;
//               break;
            case "/":
                if (!nt2.equals("")) {
                    System.out.println(str_result);
                    preResult = Double.parseDouble(nt1) / Double.parseDouble(nt2);
                }else {
                    preResult = Double.parseDouble(nt1) / 1.0;
                }
                System.out.println(preResult+"/");
                return  preResult;
            case "":
                preResult = Integer.parseInt(nt1);
            }
        return preResult;
    }
}