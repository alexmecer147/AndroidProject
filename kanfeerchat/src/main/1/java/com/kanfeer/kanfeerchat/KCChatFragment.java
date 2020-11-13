package com.kanfeer.kanfeerchat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * Use the {@link KCChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KCChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Bundle bundle;

    private String[] name;
    private String[] contents;
    private int[] avatars;
    private ListView kcChats;

//    public KCChatFragment(Bundle bundle){
//        this.bundle=bundle;
//    }

    public KCChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KCChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KCChatFragment newInstance(String param1, String param2) {
        KCChatFragment fragment = new KCChatFragment();
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

    private ChatToDetail chatToDetail;//这是定义了实现接口的对象，大约就是我们的Activity了

    public interface ChatToDetail{//这是接口以及方法，为了让Activity与Fragment粘起来
        public void kcFToA(Bundle bundle);//在Activity中调用这个方法就实现传数据了
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bundle=getArguments();
        if (context instanceof ChatToDetail) {
            chatToDetail = (ChatToDetail) context;//这样就获得了整个Activity对象
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_k_c_chat, container, false);
        LinkedList<Person> people = new LinkedList<>();
        avatars =new  int[]{R.mipmap.kc_avatar1,R.mipmap.kc_avatar2,R.mipmap.kc_avatar3};
        name =new String[]{"月亮偷走我的心","奶面包不错","眼中是你"};
        contents = new String[]{"在？建大填表","带我去浪","王者五排了"};
        for (int i = 0; i<name.length ; i++){
            Person p = new Person();
            p.setAvatar(avatars[i]);
            p.setName(name[i]);
            p.setContent(contents[i]);
            people.add(p);
            //System.out.println(i+"iiiiiiiii");
        }
        KCCAdapter kccAdapter = new KCCAdapter(getContext(), people);
        kcChats=view.findViewById(R.id.chat_list_view);
        kcChats.setAdapter(kccAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        kcChats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("position",position);
                chatToDetail.kcFToA(bundle1);
                //System.out.println("A"+bundle1.getInt("position"));
            }
        });

    }

    class Person {
        private int avatar;
        private String name;
        private String content;

        public Person() {
        }

        public Person(int avatar, String name, String content) {
            this.avatar = avatar;
            this.name = name;
            this.content = content;
        }

        public int getAvatar() {
            return avatar;
        }

        public void setAvatar(int avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    class KCCAdapter extends BaseAdapter {

        Context context;
        LinkedList<Person> People;

        public KCCAdapter(Context context, LinkedList<Person> people) {
            this.context = context;
            People = people;
        }

        @Override
        public int getCount() {
            return People.size();
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
        public View getView(final int position, View convertView, final ViewGroup parent) {
            convertView=LayoutInflater.from(this.context).inflate(R.layout.chat_item_detail_layout,parent,false);
            ImageView avatar = convertView.findViewById(R.id.kc_contacts_avatar);
            TextView name = convertView.findViewById(R.id.kc_contacts_name);
            TextView content = convertView.findViewById(R.id.kc_contacts_content);
            avatar.setImageResource(People.get(position).getAvatar());
            name.setText(People.get(position).getName());
            content.setText(People.get(position).getContent());
            return convertView;
        }
    }
}