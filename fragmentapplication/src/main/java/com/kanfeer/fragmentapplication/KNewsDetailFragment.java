package com.kanfeer.fragmentapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private TextView kNewsName;
    private TextView kNewsDetailContent;
    private ImageView kNewsPhoto;

    private String[] thenews = {"新闻1","欣慰2","新闻3","新闻3","新闻4","新闻5","新闻6"};
    private int[] avatar = {R.mipmap.newsphoto1,R.mipmap.newsphoto2,R.mipmap.newsphoto3,R.mipmap.newsphoto4,R.mipmap.newsphoto5,R.mipmap.newphoto6};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        kNewsName=getActivity().findViewById(R.id.KNewsName);
        kNewsDetailContent=getActivity().findViewById(R.id.KNewsDetailContent);
        kNewsPhoto=getActivity().findViewById(R.id.KNewsPhoto);

        Bundle bundle = getArguments();
        kNewsName.setText(bundle.getString("name"));
        kNewsPhoto.setImageResource(avatar[bundle.getInt("position")]);
        kNewsDetailContent.setText(thenews[bundle.getInt("position")]);
        //kNewsName.setText();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_k_news_detail, container, false);
    }
}