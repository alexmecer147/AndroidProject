package com.kanfeer.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private ListView landlordCommentListView;
    private ListView reviewerCommentListView;
    private List<Map<String,Object>> landlordList;
    private List<Map<String,Object>> reviewerList;
    private String landlordNameStr = "肖战我爱你";
    private String[] reviewerNameStr = {"李现粉丝","王俊凯粉丝"};
    private int landlordPhoto = R.mipmap.head_photo1;
    private int[] reviewerPhoto = {R.mipmap.head_photo2,R.mipmap.head_photo3};
    private String landlordCommentStr = "今天天气如何?";
    private String[] reviewerCommentStr = {"今天天气很热!","今天王俊凯生日!"};

    private SimpleAdapter landlordSA;
    private SimpleAdapter reviewerSA;

    private Button commentButton;
    private Button likeButton;
    private ImageView thumbImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateStr=simpleDateFormat.format(date);

        landlordCommentListView = findViewById(R.id.landlord_comment_view);
        reviewerCommentListView = findViewById(R.id.reviewer_comment_view);
        reviewerList = new ArrayList<>();
        for (int i = 0; i < reviewerNameStr.length ; i++ ){
            Map<String, Object> reviewerMap = new HashMap<>();
            reviewerMap.put("reviewerNameStr",reviewerNameStr[i]);
            reviewerMap.put("reviewerPhoto",reviewerPhoto[i]);
            reviewerMap.put("reviewerTime",dateStr);
            reviewerMap.put("reviewerCommentStr",reviewerCommentStr[i]);
            reviewerList.add(reviewerMap);
        }
        reviewerSA = new SimpleAdapter(getApplicationContext(),reviewerList, R.layout.activity_comment_item,
                new String[]{"reviewerNameStr","reviewerPhoto","reviewerTime","reviewerCommentStr"},
                new int[]{R.id.commenter_name,R.id.commenter_img,R.id.comment_time,R.id.comment_self});
        reviewerCommentListView.setAdapter(reviewerSA);

        Map<String, Object> landlordMap = new HashMap<>();
        landlordList = new ArrayList<>();
        landlordMap.put("landlordName",landlordNameStr);
        landlordMap.put("landlordPhoto",landlordPhoto);
        landlordMap.put("landlordTime", dateStr);
        landlordMap.put("landlordCommentStr", landlordCommentStr);
        landlordList.add(landlordMap);
        landlordSA = new SimpleAdapter(this,landlordList,R.layout.activity_comment_item,
                new String[]{"landlordName","landlordPhoto","landlordTime","landlordCommentStr"},
                new int[]{R.id.commenter_name,R.id.commenter_img,R.id.comment_time,R.id.comment_self});
        landlordCommentListView.setAdapter(landlordSA);

        commentButton = findViewById(R.id.comment_button);
        likeButton = findViewById(R.id.like_button);
        thumbImg = findViewById(R.id.thumb_photo);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbImg.setVisibility(0);
            }
        });
    }
}