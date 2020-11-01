package com.kanfeer.thenewadaptercommentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;

import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainCommentActivity extends AppCompatActivity {

    //private ListView commenterView;
    static ListView mainCommenterList;
    static LinkedList<Commenter> commenter = new LinkedList<>();
    private ListView mainReviewerList;
    static LinkedList<Commenter> reviewers = new LinkedList<>();
    private int[] headPhotos;
    private String[] names;
    private String[] times;
    private String[] comments;

    static String dateStr;

    private Button buttonView;
    private Button buttonLike;
    private EditText viewEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comment);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        dateStr=simpleDateFormat.format(date);

        mainCommenterList = findViewById(R.id.new_commenter_list_view);
        mainReviewerList = findViewById(R.id.new_reviewer_list_view);
        names = new String[] {"肖战粉丝","李现粉丝","王俊凯粉丝"};
        times = new String[] {"2020-09-09","2020-02-89","2020-8-5"};
        comments = new String[] {"今天天气如何","今天天气很热!","今天王俊凯生日!"};
        headPhotos = new int[] {R.mipmap.head_photo1, R.mipmap.head_photo2,R.mipmap.head_photo3};


        buttonView = findViewById(R.id.button_view);
        buttonLike = findViewById(R.id.button_like);
        viewEditText = findViewById(R.id.view_edit_text);


//        Map<String, Object> commenterMap = new HashMap<>();
//        commenterMap.put("headPhoto",headPhotos[0]);
//        commenterMap.put("names",names[0]);
//        commenterMap.put("times",times[0]);
//        commenterMap.put("comments",comments[0]);
//        commenterMap.put("thumbPhoto",thumbPhoto);

        commenter.add(new Commenter(headPhotos[0],names[0],times[0],comments[0],true));
        CommentAdapter commentAdapter = new CommentAdapter(commenter,this);
        mainCommenterList.setAdapter(commentAdapter);

        for (int i = 1; i < names.length; i++){
            reviewers.add(new Commenter(headPhotos[i],names[i],times[i],comments[i],false));
        }

        final CommentAdapter reviewerAdapter = new CommentAdapter(reviewers,this);
        mainReviewerList.setAdapter(reviewerAdapter);

        buttonView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                if (viewEditText.getText().toString().trim().length() == 0){
                    Toast.makeText(getApplicationContext(),"请输入你要评论的内容，评论内容禁止为空",Toast.LENGTH_SHORT).show();
                }else {
                   // mainReviewerList.notify();
//                    reviewers.clear();
//                    for (int i = 1; i < names.length; i++){
//                        reviewers.add(new Commenter(headPhotos[i],names[i],times[i],comments[i],false));
//                    }
//                    Commenter newCommenter = new Commenter(R.mipmap.head_photo4,"吴彦祖",dateStr,viewEditText.getText().toString(),false);
//
//                    viewEditText.setText("");
//                    reviewers.add(newCommenter);
//                    final CommentAdapter reviewerAdapter = new CommentAdapter(reviewers,getApplicationContext());
//                    if (mainReviewerList.getAdapter() != reviewerAdapter) {
//                    mainReviewerList.setAdapter(reviewerAdapter);
//                    }
                    Commenter newCommenter = new Commenter(R.mipmap.head_photo4,"吴彦祖",dateStr,viewEditText.getText().toString(),false);
                    reviewers.add(newCommenter);
                    reviewerAdapter.notifyDataSetChanged();

                }
            }
        });



    }

    static class Commenter {

        private int headPhoto;
        private String commentName;
        private String commentDatetime;
        private String comment;
        private boolean isCommenter;

        public Commenter(int headPhoto, String commentName, String commentDatetime, String comment,boolean isCommenter) {
            this.headPhoto = headPhoto;
            this.commentName = commentName;
            this.commentDatetime = commentDatetime;
            this.comment = comment;
            this.isCommenter = isCommenter;
        }

        public int getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(int headPhoto) {
            this.headPhoto = headPhoto;
        }

        public String getCommentName() {
            return commentName;
        }

        public void setCommentName(String commentName) {
            this.commentName = commentName;
        }

        public String getCommentDatetime() {
            return commentDatetime;
        }

        public void setCommentDatetime(String commentDatetime) {
            this.commentDatetime = commentDatetime;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public boolean isCommenter() {
            return isCommenter;
        }

        public void setCommenter(boolean commenter) {
            isCommenter = commenter;
        }
    }

    class CommentAdapter extends BaseAdapter{

        public CommentAdapter(LinkedList<Commenter> commenterList, Context context) {
            this.commenterList = commenterList;
            this.context = context;
        }

        private final LinkedList<Commenter> commenterList;
        private final Context context;

        @Override
        public int getCount() {
            return commenterList.size();
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

            convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_comment, parent, false);
            LinearLayout layoutLinear = convertView.findViewById(R.id.activityComment);
            ImageView imgHead = convertView.findViewById(R.id.commenter_img);
            TextView commentName = convertView.findViewById(R.id.commenter_name);
            TextView commentTime = convertView.findViewById(R.id.comment_time);
            TextView comment = convertView.findViewById(R.id.comment_self);
            final ImageView thumbImg = convertView.findViewById(R.id.thumb_photo);
            //TextView test = convertView.findViewById(R.id.test);

            imgHead.setImageResource(commenterList.get(position).getHeadPhoto());
            commentName.setText(commenterList.get(position).getCommentName());
            commentTime.setText(commenterList.get(position).getCommentDatetime());
            comment.setText(commenterList.get(position).getComment());

            int i = 1;
            if (commenterList.get(position).isCommenter()&&position == 0){
                thumbImg.setImageAlpha(80);
                buttonLike.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (thumbImg.getImageAlpha() < 255){
                            thumbImg.setImageAlpha(255);
                            buttonLike.setText("已点赞");
                        }else {
                            thumbImg.setImageAlpha(80);
                            buttonLike.setText("点赞");
                        }
                    }
                });
                commentName.setText(commenterList.get(position).getCommentName()+"(楼主)");
                //test.setText("commenter");
                thumbImg.setImageAlpha(80);
                thumbImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (thumbImg.getImageAlpha() < 255){
                            thumbImg.setImageAlpha(255);
                        }else {
                            thumbImg.setImageAlpha(80);
                        }
                    }
                });
            }else {
                //test.setText("reviewer");
                layoutLinear.setPadding(80,0,0,0);
                thumbImg.setImageAlpha(0);
            }
            return convertView;
        }
    }

}
