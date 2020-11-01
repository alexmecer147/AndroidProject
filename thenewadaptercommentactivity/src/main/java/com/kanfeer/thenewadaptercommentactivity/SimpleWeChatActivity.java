package com.kanfeer.thenewadaptercommentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimpleWeChatActivity extends AppCompatActivity {

    ListView wechatListView;
    LinkedList<WeChater> wechatList = new LinkedList<>();
    Button addView;
    EditText wechatCommentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_wechat);
        wechatListView=findViewById(R.id.wechat_item);
        addView = findViewById(R.id.wechat_view_button);
        wechatCommentEditText=findViewById(R.id.wechat_comment_editText);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        final String dateStr=simpleDateFormat.format(date);

        String[] names = new String[]{"猎人","法师","牧师"};
        String[] comments = new String[]{"永恒的狩猎开始了！","这可是你自找的！","神光庇护着我！"};
        String[] commentTimes = new String[]{"2020-03-04","2020-04-05","2020-05-07"};
        int[] headImages = new int[]{R.mipmap.wechat_head_photo1,R.mipmap.wechat_head_photo2,R.mipmap.wechat_head_photo3};

        for (int i = 0; i < names.length ; i++){
            WeChater wechater = new WeChater(names[i],commentTimes[i],comments[i],headImages[i]);
            wechatList.add(wechater);
        }

        final WeChatAdapter wca = new WeChatAdapter(wechatList,this);
        wechatListView.setAdapter(wca);

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeChater t = new WeChater("路怀阳",dateStr,wechatCommentEditText.getText().toString(),R.mipmap.wechat_head_photo4);
                wechatList.add(t);
                wca.notifyDataSetChanged();
            }
        });
    }
}

class WeChater implements Serializable {
    private String name;
    private String postTime;
    private String postContent;
    private int headPhoto;

    public WeChater() {
    }

    public WeChater(String name, String postTime, String postContent, int headPhoto) {
        this.name = name;
        this.postTime = postTime;
        this.postContent = postContent;
        this.headPhoto = headPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(int headPhoto) {
        this.headPhoto = headPhoto;
    }
}

class WeChatAdapter extends BaseAdapter{

    private LinkedList<WeChater> wcaList;
    private Context wcaContext;

    public WeChatAdapter(LinkedList<WeChater> wcaList, Context wcaContext) {
        this.wcaList = wcaList;
        this.wcaContext = wcaContext;
    }

    @Override
    public int getCount() {
        return wcaList.size();
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

        convertView = LayoutInflater.from(this.wcaContext).inflate(R.layout.wechat_item_layout,parent,false);
        ImageView wechatHead = convertView.findViewById(R.id.wechat_commenter_img);
        TextView wechaterName = convertView.findViewById(R.id.wechat_commenter_name);
        TextView wechatCommentTime = convertView.findViewById(R.id.wechat_comment_time);
        TextView wechatComment = convertView.findViewById(R.id.wechat_comment_self);

        wechatHead.setImageResource(wcaList.get(position).getHeadPhoto());
        wechaterName.setText(wcaList.get(position).getName());
        wechatCommentTime.setText(wcaList.get(position).getPostTime());
        wechatComment.setText(wcaList.get(position).getPostContent());



        return convertView;
    }
}