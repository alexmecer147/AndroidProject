package com.kanfeer.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Collections;

import java.util.LinkedList;

public class VoteActivity extends AppCompatActivity {

    static ListView voteCourseList;
    //SimpleAdapter voteSimpleAdapter;
    static LinkedList<Object> objectList = new LinkedList<>();
    Button ascButton;
    Button desButton;

    static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        voteCourseList = this.findViewById(R.id.vote_object_list);
        voteCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView view1 = (TextView) parent.getItemAtPosition(position);
                //Map<String,Object> map = (Map<String, Object>) voteCourseList.getItemAtPosition(position);

//                TextView sv = view.findViewById(R.id.vote_item_name);
//                ProgressBar pb = view.findViewById(R.id.vote_progressbar_count);
//                Bundle bundle = new Bundle();
//                Intent intent = new Intent(VoteActivity.this,ItemDetailActivity.class);
//                Object ps = new Object(sv.getText().toString(),pb.getProgress());
//                bundle.putSerializable("ps",ps);
//                intent.putExtras(bundle);
//                startActivity(intent);

                TextView name = view.findViewById(R.id.vote_item_name);
                ProgressBar pb = view.findViewById(R.id.vote_progressbar_count);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(VoteActivity.this,ItemDetailActivity.class);
                bundle.putString("name",name.getText().toString());
                bundle.putInt("count",pb.getProgress());
                bundle.putInt("position",position);

//                bundle.putString("name","name.getText().toString()");
//                bundle.putInt("count",11);
                intent.putExtras(bundle);
                startActivity(intent);

//                EditText name = findViewById(R.id.register_name);
//                EditText password = findViewById(R.id.register_password);
//                RadioButton male = findViewById(R.id.male);
//                String gender = male.isChecked() ? "男" : "女";
//                Person p = new Person(name.getText().toString(),
//                        password.getText().toString(),gender);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("person", p);
//                Intent intent = new Intent(IntentPrecticeActivity.this,
//                        RegisterResultActivity.class);
//                intent.putExtras(bundle);

            }
        });

        listToAdapter();

        ascButton = findViewById(R.id.ascending_order_button);
        desButton = findViewById(R.id.descending_order_button);

        ascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                Collections.sort(objectList,Collections.<Object>reverseOrder());
                //System.out.print(objectList.pop().toString());
                listToAdapter();

            }
        });
        desButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                Collections.sort(objectList);
                //System.out.print(objectList.pop().toString());
                listToAdapter();
            }
        });




    }

    public void listToAdapter(){
        if (i==0){
            objectList.clear();
            objectList.add(new Object("软件工程",0,1));
            objectList.add(new Object("土木工程",0,2));
            objectList.add(new Object("网络工程",0,3));
            objectList.add(new Object("工程管理",0,4));
            objectList.add(new Object("专业英语",0,5));
            TheObjectAdapter objectAdapter = new TheObjectAdapter(objectList,this);
            voteCourseList.setAdapter(objectAdapter);
        }else {
            TheObjectAdapter objectAdapter = new TheObjectAdapter(objectList,this);
            voteCourseList.setAdapter(objectAdapter);
        }
    }

    static class Object implements Comparable<Object>, Serializable {

        private String name;
        private int count;
        private int photo;

        public Object(String name, int count,int photo){
            this.name=name;
            this.count=count;
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return (int) count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPhoto() {
            return photo;
        }

        public void setPhoto(int photo) {
            this.photo = photo;
        }

        @Override
        public int compareTo(Object p) {
            if (this.getCount() < p.getCount()){
                return 1;
            }else if (this.getCount() > p.getCount()){
                return -1;
            }else {
                return 0;
            }
        }
    }

    private static Toast toast ;
    static boolean isShowing = false;
    public void showToast(){
        if (isShowing == false) {
            toast = Toast.makeText(getApplicationContext(), R.string.toast_string, Toast.LENGTH_SHORT);
            toast.show();
            isShowing = true;
        }else {
            toast.setText(R.string.toast_string);
            isShowing = false;
            toast.cancel();
        }
    }

    class TheObjectAdapter extends BaseAdapter{
        private LinkedList<Object> listObject;//在这个Adapter的展示的数据集合
        private Context context;

        public TheObjectAdapter(LinkedList<Object> objects,Context context){
            this.listObject = objects;
            this.context = context;
        }
        @Override
        public int getCount() {
            return listObject.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {//返回第position的id，一般用position代替
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.voteitemlayout,parent,false);
            TextView voteItemName = convertView.findViewById(R.id.vote_item_name);
            final TextView voteItemNum = convertView.findViewById(R.id.vote_progressbar_count_number);
            final ProgressBar voteProgressCount = convertView.findViewById(R.id.vote_progressbar_count);
            Button addProgressButton = convertView.findViewById(R.id.add_progress_button);

            voteItemName.setText(objectList.get(position).getName());
            voteItemNum.setText(String.valueOf(objectList.get(position).getCount()));
            voteProgressCount.setProgress(objectList.get(position).getCount());
            addProgressButton.setFocusable(false);
            addProgressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    objectList.get(position).setCount(objectList.get(position).getCount()+1);
                    voteProgressCount.setProgress(objectList.get(position).getCount());
                    voteItemNum.setText(String.valueOf(objectList.get(position).getCount()));
                    showToast();
                }
            });
            return convertView;
        }
    }

}
