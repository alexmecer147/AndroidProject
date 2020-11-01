package com.kanfeer.fragmentapplication.store;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kanfeer.fragmentapplication.R;


public class DanceWithFragmentActivity extends AppCompatActivity {

    FirstFragment firstFragment;
    AnotherFragment anotherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_with_fragment);

        firstFragment = new FirstFragment();
        anotherFragment = new AnotherFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.oupfirstfragment1,firstFragment);
        //fragmentTransaction.add(R.id.oupfirstfragment2,anotherFragment);
        fragmentTransaction.commit();

        fragmentTransaction.replace(R.id.oupfirstfragment1,anotherFragment);
        fragmentTransaction.commit();
    }

}