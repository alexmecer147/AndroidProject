package com.kanfeer.fragmentapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KNewsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KNewsDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private KanfeerChatActivityWithFragments mainActivity;
    private TextView kNewsName;
    private TextView kNewsDetailContent;
    private ImageView kNewsPhoto;

    private final String[] thenews = {"新闻1","欣慰2","新闻3","新闻3","新闻4","新闻5","新闻6"};
    private final int[] avatar = {R.mipmap.newsphoto1,R.mipmap.newsphoto2,R.mipmap.newsphoto3,R.mipmap.newsphoto4,R.mipmap.newsphoto5,R.mipmap.newphoto6};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private Context mainContext;

    public KNewsDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KNewsDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KNewsDetailFragment newInstance(String param1, String param2) {
        KNewsDetailFragment fragment = new KNewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof KanfeerChatActivityWithFragments){
            mainActivity = (KanfeerChatActivityWithFragments) context;
        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_k_news_detail, container, false);
        kNewsName = view.findViewById(R.id.KNewsName);
        kNewsDetailContent = view.findViewById(R.id.KNewsDetailContent);
        kNewsPhoto = view.findViewById(R.id.KNewsPhoto);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle.isEmpty()){
            System.out.println("kkkkkkkkkk");
        }else {
            System.out.println("nnnnnno empty");
        }
        kNewsName.setText((String)bundle.get("name"));
        kNewsPhoto.setImageResource(avatar[bundle.getInt("position")]);
        kNewsDetailContent.setText(thenews[bundle.getInt("position")]);
        //kNewsName.setText();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainActivity.hideOthers(2);
    }
}