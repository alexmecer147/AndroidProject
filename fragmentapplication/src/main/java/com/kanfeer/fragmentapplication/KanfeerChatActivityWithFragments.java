package com.kanfeer.fragmentapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class KanfeerChatActivityWithFragments extends AppCompatActivity implements KNewsFragment.FragToActivity{

    private TextView KCTitle;
    //private Bundle bundle1;
    private KNewsFragment.FragToActivity fragToActivity;
    private KFriendsFragment kFriendsFragment;
    private KNewsFragment kNewsFragment;
    private KNewsDetailFragment kNewsDetailFragment;
    private FragmentManager fragmentManager;
    //int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanfeer_chat_main_with_three_fragment);
        kFriendsFragment = new KFriendsFragment();
        kNewsFragment = new KNewsFragment();


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionA = fragmentManager.beginTransaction();
        // FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        //kNewsFragment.setArguments(bundle1);
        fragmentTransactionA.add(R.id.KCMainFragment,kFriendsFragment);
        fragmentTransactionA.add(R.id.KCMainFragment,kNewsFragment);
        //fragmentTransactionA.add(R.id.KCMainFragment,kNewsDetailFragment);
        hideOthers(1);
//        fragmentTransaction2.add(R.id.KCMainFragment,kNewsFragment);
//        fragmentTransaction2.hide(kNewsFragment);
        fragmentTransactionA.commit();

        KCTitle = findViewById(R.id.KCTitle);
        Button fbtn = findViewById(R.id.NavigatorFriends);
        Button nbtn = findViewById(R.id.NavigatorNews);
        Button ubtn = findViewById(R.id.NavigatorUser);
        Button sbtn = findViewById(R.id.NavigatorSetting);

        fbtn.setOnClickListener(v -> {
            KCTitle.setText("消息");
//                FragmentTransaction fragmentTransactionF = fragmentManager.beginTransaction();
            hideOthers(1);
            //fragmentTransaction1.commit();
            //fragmentTransactionF.commit();
        });

        nbtn.setOnClickListener(v -> {
            hideOthers(2);
            KCTitle.setText("新闻");
//                FragmentTransaction fragmentTransactionN = fragmentManager.beginTransaction();
//                //fragmentTransaction.
//                fragmentTransactionN.replace(R.id.KCMainFragment,kNewsFragment);
//                //fragmentTransaction1.commit();
//                fragmentTransactionN.commit();
        });
        ubtn.setOnClickListener(v -> KCTitle.setText("个人"));
        sbtn.setOnClickListener(v -> KCTitle.setText("设置"));
    }

    public void hideOthers(int i){
        FragmentTransaction p = fragmentManager.beginTransaction();
        if (i == 1){
            p.hide(kNewsFragment);
            if (kNewsDetailFragment!=null) {
                p.hide(kNewsDetailFragment);
            }
            p.show(kFriendsFragment);
        }else if (i == 2){
            p.hide(kFriendsFragment);
            if (kNewsDetailFragment!=null) {
                p.hide(kNewsDetailFragment);
            }
            p.show(kNewsFragment);
        }else if (i == 3){
            p.hide(kFriendsFragment);
            p.hide(kNewsFragment);
            p.show(kNewsDetailFragment);
        }
        p.commit();
    }

    @Override
    public void FTAMethod(Bundle bundle) {
//        bundle1 = bundle;
        FragmentTransaction fragmentTransactionND = fragmentManager.beginTransaction();
        fragmentTransactionND.addToBackStack(null);//只返回到上一个fragment界面

        kNewsDetailFragment = new KNewsDetailFragment();
        kNewsDetailFragment.setArguments(bundle);
        if (bundle.isEmpty()){
            System.out.println("main empty");
        }else {
            System.out.println("main not empty");
        }
        fragmentTransactionND.add(R.id.KCMainFragment,kNewsDetailFragment);
        fragmentTransactionND.commit();
        hideOthers(3);
    }
}