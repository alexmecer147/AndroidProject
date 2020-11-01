package com.kanfeer.fragmentapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KFriendsFragment extends Fragment {


    private LinkedList<Person> friends;
    private PAdapter friendsAdapter;
    private String[] names = {"小红","小白","小黑"};
    private String[] conversations={"智慧建大填表","代码发我一份","为什么没有我的名字"};
    private int[] avatars = {R.mipmap.avatar1,R.mipmap.avatar2,R.mipmap.avatar3};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KFriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KFriendsFragment newInstance(String param1, String param2) {
        KFriendsFragment fragment = new KFriendsFragment();
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
        ListView KFLV = getActivity().findViewById(R.id.KFriendListView);
        friends = new LinkedList<>();
        for (int i = 0; i < names.length ;i++){
            Person p = new Person(avatars[i],names[i],conversations[i]);
            friends.add(p);
        }
        PAdapter FAdapter = new PAdapter(friends,getContext());
        KFLV.setAdapter(FAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_k_friends, container, false);
    }

    class Person{
        private int avatar;
        private String name;
        private String conversation;

        public Person(int avatar, String name, String conversation) {
            this.avatar = avatar;
            this.name = name;
            this.conversation = conversation;
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

        public String getConversation() {
            return conversation;
        }

        public void setConversation(String conversation) {
            this.conversation = conversation;
        }
    }

    class PAdapter extends BaseAdapter{
        private LinkedList<Person> persons;
        private Context context;

        public PAdapter(LinkedList<Person> persons, Context context) {
            this.persons = persons;
            this.context = context;
        }

        @Override
        public int getCount() {
            return persons.size();
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
            convertView=LayoutInflater.from(this.context).inflate(R.layout.person_item_layout,parent,false);
            ImageView avatar = convertView.findViewById(R.id.personItemAvatar);
            TextView name = convertView.findViewById(R.id.personItemName);
            TextView conversation = convertView.findViewById(R.id.personItemConversation);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            avatar.setImageResource(persons.get(position).getAvatar());
            name.setText(persons.get(position).getName());
            conversation.setText(persons.get(position).getConversation());
            return convertView;
        }
    }
}