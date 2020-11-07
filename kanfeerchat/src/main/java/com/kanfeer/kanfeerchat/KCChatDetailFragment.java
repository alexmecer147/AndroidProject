package com.kanfeer.kanfeerchat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KCChatDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KCChatDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Bundle bundle;

    private String[] chatList;
    private int[] avatars;

    public KCChatDetailFragment() {
        // Required empty public constructor
    }

    //public KCChatDetailFragment(Bundle bundle){
//        this.bundle=bundle;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KCChatDetailFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static KCChatDetailFragment newInstance(String param1, String param2) {
        KCChatDetailFragment fragment = new KCChatDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bundle=getArguments();
        //System.out.println(bundle.getInt("position")+"fromCC");
        if (context instanceof MainPageActivity){
             mainPageActivity = (MainPageActivity) context;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundle = getArguments();
        assert bundle != null;
        System.out.println("detail_bundle"+bundle.getInt("position"));
    }

    private MainPageActivity mainPageActivity;

    @Override
    public void onStop() {
        super.onStop();
        mainPageActivity.returnFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinkedList<chatitem> chatslists = new LinkedList<>();
        View view = inflater.inflate(R.layout.fragment_k_c_chat_detail, container, false);

        ListView chatdetaillistview = view.findViewById(R.id.chat_detail_list_view);
        String[] mychat = {"可以了","还行吧","一半一半","我不行了","我就这样"};
        avatars = new int[]{R.mipmap.kc_avatar1,R.mipmap.kc_avatar2,R.mipmap.kc_avatar3};
        switch (bundle.getInt("position")){
            case 0:
                chatList = new String[]{"我要干饭","我们要干饭","送快递","你好锕","吃了吗","饿了么","晚安啦"};
                break;
            case 1:
                chatList = new String[]{"上海","我要干饭","我们要干饭","送快递","订外卖","我也饿了","晚上饿了","白天也饿了","中午下课了"};
                break;
            case 2:
                chatList = new String[]{"厉害","牛啊","可以可以","chi点东西","我觉得我可以","我觉得你不想","我厉害了","你厉害了"};
                break;
        }
        int j = 0;
        for (int i = 0; i<5; i++){
            chatitem chatitem = new chatitem();
            if (j==0) {
                chatitem.setAvatar(avatars[bundle.getInt("position")]);
                chatitem.setWords(chatList[i]);
                chatitem.setFriends(true);
                j++;
            }else {
                chatitem.setWords(mychat[i]);
                chatitem.setFriends(false);
                j--;
            }
            chatslists.add(chatitem);
        }
        chatAdapter theChatAdapter = new chatAdapter(getContext(), chatslists);
        chatdetaillistview.setAdapter(theChatAdapter);
        return view;
    }
    static class chatitem{
        private boolean isFriends;
        private int avatar;
        private String words;

        public chatitem() {
        }

        public chatitem(boolean isFriends, int avatar, String words) {
            this.isFriends = isFriends;
            this.avatar = avatar;
            this.words = words;
        }

        public boolean isFriends() {
            return isFriends;
        }

        public void setFriends(boolean friends) {
            isFriends = friends;
        }

        public int getAvatar() {
            return avatar;
        }

        public void setAvatar(int avatar) {
            this.avatar = avatar;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }

    class chatAdapter extends BaseAdapter{

        private Context context;
        private LinkedList<chatitem> chats;

        public chatAdapter(Context context, LinkedList<chatitem> chats) {
            this.context = context;
            this.chats = chats;
        }

        @Override
        public int getCount() {
            return chats.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (chats.get(position).isFriends) {
                convertView = LayoutInflater.from(this.context).inflate(R.layout.left_chats_item_layout, parent, false);
                TextView words = convertView.findViewById(R.id.dragLeft_chats_blank);
                ImageView avatar = convertView.findViewById(R.id.dragLeft_avatar_blank);
                words.setText(chats.get(position).getWords());
                avatar.setImageResource(chats.get(position).getAvatar());
                return convertView;
            }else {
                convertView = LayoutInflater.from(this.context).inflate(R.layout.right_chats_item_layout, parent, false);
                TextView words = convertView.findViewById(R.id.dragRight_chats_blank);
                ImageView avatar = convertView.findViewById(R.id.dragRight_avatar_blank);
                words.setText(chats.get(position).getWords());
                avatar.setImageResource(R.mipmap.kc_avatar4);
                return convertView;
            }
        }
    }
}
