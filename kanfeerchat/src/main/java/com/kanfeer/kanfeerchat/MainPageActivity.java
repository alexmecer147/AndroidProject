package com.kanfeer.kanfeerchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainPageActivity extends AppCompatActivity implements KCChatFragment.ChatToDetail{

    private KCZoneFragment kcZoneFragment;
    private KCChatFragment kcChatFragment;
    private KCConnectersFragment kcConnectersFragment;
    private KCChatDetailFragment kcChatDetailFragment;
    private FragmentManager fragmentManager;
    private RadioButton chatbutton;
    private RadioButton connectorbutton;
    private RadioButton zonebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page2);
        Intent it = getIntent();
        Bundle bd = it.getExtras();
        System.out.println(bd.getString("iname")+"fromMainPage");
        chatbutton=findViewById(R.id.kc_chat_button);
        connectorbutton=findViewById(R.id.kc_connector_button);
        zonebutton=findViewById(R.id.kc_zone_button);
        kcZoneFragment=new KCZoneFragment();
        kcChatFragment=new KCChatFragment();
        kcConnectersFragment=new KCConnectersFragment();
        //kcChatDetailFragment=new KCChatDetailFragment();
        kcZoneFragment.setArguments(bd);
        kcChatFragment.setArguments(bd);
        kcConnectersFragment.setArguments(bd);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.kcfragment_container,kcChatFragment);
        fragmentTransaction.add(R.id.kcfragment_container,kcConnectersFragment);
        fragmentTransaction.add(R.id.kcfragment_container,kcZoneFragment);
        //fragmentTransaction.add(R.id.kcfragment_container,kcChatDetailFragment);
        fragmentTransaction.hide(kcConnectersFragment);
        fragmentTransaction.hide(kcZoneFragment);
        //fragmentTransaction.hide(kcChatDetailFragment);
        fragmentTransaction.commit();

        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                hideAll();
                fragmentTransaction1.show(kcChatFragment);
                fragmentTransaction1.commit();
            }
        });
        connectorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                hideAll();
                fragmentTransaction1.show(kcConnectersFragment);
                fragmentTransaction1.commit();
            }
        });
        zonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                hideAll();
                fragmentTransaction1.show(kcZoneFragment);
                fragmentTransaction1.commit();
            }
        });

//        kcFToA();
    }

    public void hideAll(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (kcConnectersFragment!=null){
            fragmentTransaction.hide(kcConnectersFragment);
        }
        if (kcChatFragment!=null){
            fragmentTransaction.hide(kcChatFragment);
        }
        if (kcZoneFragment!=null){
            fragmentTransaction.hide(kcZoneFragment);
        }
        if (kcChatDetailFragment!=null){
            fragmentTransaction.hide(kcChatDetailFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void kcFToA(Bundle bundle) {
        System.out.println(bundle.getInt("position"));
        kcChatDetailFragment = new KCChatDetailFragment();
        kcChatDetailFragment.setArguments(bundle);
        hideAll();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.kcfragment_container,kcChatDetailFragment);
        fragmentTransaction1.show(kcChatDetailFragment);
        fragmentTransaction1.commit();
    }




//    public void replaceFragment(){
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.kcfragment_container,kcChatDetailFragment);
////        fragmentTransaction.remove(kcChatFragment);
////        fragmentTransaction.add(R.id.kcfragment_container,kcChatDetailFragment);
//        fragmentTransaction.commit();
//    }
}