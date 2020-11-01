package com.kanfeer.fragmentapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KNewsFragment extends Fragment {

    private ListView KNewsListView;
    private ArrayList newsList;
    private String[] news = {"喀什已基本排除疫情蔓延可能性","中国人均预期寿命增加近1岁","专家回应大陆军机巡台常态化","外交部回应蓬佩奥再提中国威胁","人社部:确保养老金按时足额发放","一颗价值超2亿钻石运抵上海"};
    private ArrayAdapter newsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KNewsFragment newInstance(String param1, String param2) {
        KNewsFragment fragment = new KNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KNewsListView = getActivity().findViewById(R.id.KNewsListView);
        newsAdapter = new ArrayAdapter(getContext(),R.layout.newslistitemlayout,news);
        KNewsListView.setAdapter(newsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        KNewsListView.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putString("name",news[position]);
            bundle.putInt("position",position);
            FTAListener.FTAMethod(bundle);//数据传给Activity
        });
        return inflater.inflate(R.layout.fragment_k_news, container, false);
    }

    public interface FragToActivity{
        public void FTAMethod(Bundle bundle);
    }

    private FragToActivity FTAListener;//定义接口类型变量
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragToActivity){
            FTAListener = (FragToActivity) context;
        }
    }

    public void onDetach() {
        super.onDetach();
        FTAListener = null;
    }

}