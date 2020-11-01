package com.example.helloandroid.fragtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.helloandroid.R;


public class PutUFragOnThisActivity extends AppCompatActivity {


    Button changebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_u_frag_on_this);

        changebutton = findViewById(R.id.change_frag_button);

        final ThisUFragment1 onefragment = new ThisUFragment1();
        final ThisUFragment2 twofragment = new ThisUFragment2();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout1,twofragment);
//        fragmentTransaction.add(R.id.fragment_layout1,onefragment);
//        fragmentTransaction.hide(onefragment);
        fragmentTransaction.commit();
        //fragmentTransaction.commit();

        changebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.remove(twofragment);
//                fragmentTransaction.remove(onefragment);
                fragmentTransaction1.add(R.id.fragment_layout1,onefragment);
                fragmentTransaction1.commit();
            }

        });

    }
}