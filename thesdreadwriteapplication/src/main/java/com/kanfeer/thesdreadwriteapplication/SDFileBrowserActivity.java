package com.kanfeer.thesdreadwriteapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.LinkedList;

public class SDFileBrowserActivity extends AppCompatActivity {

    private static TextView filePath;
    private static ListView fileListView;
    private static Button buttonReturn;
    private static String lastDirectory;

    private static LinkedList<FileItem> fileLists;
    private static FileAdapter fileAdapter;

    private static SDFileHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_file_browser);

        buttonReturn=findViewById(R.id.button_directory_return);
        filePath = findViewById(R.id.textView_SD_file_path);
        fileListView=findViewById(R.id.listView_paths);

        fileLists = new LinkedList<>();
        lastDirectory=Environment.getExternalStorageDirectory().getPath();
        helper=new SDFileHelper(this);

        //requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x123);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filePath.setText(lastDirectory);
            Toast.makeText(getApplicationContext(),"加载SD卡成功",Toast.LENGTH_SHORT).show();
            File file = Environment.getExternalStorageDirectory();
            File files1 = Environment.getExternalStorageDirectory();
            System.out.println("file.getName"+file.getName());//---0
            System.out.println("file.list.length"+file.list().length);//---12
            String[] filenames = file.list();//
            File[] files = file.listFiles();//获得当前文件和文件夹
            for (int i=0;i<filenames.length;i++){
                FileItem fi;
                //System.out.println("filenames["+i+"]:"+filenames[i]);//filenames[12]:Newwww
                //System.out.println("files["+i+"]:"+files[i]);//files[12]:/storage/emulated/0/Newwww
                if (files[i].isDirectory()) {
                    fi = new FileItem(R.mipmap.direcroty_icon, filenames[i],files[i].getAbsolutePath());
                    System.out.println("file["+i+"] is "+"directory and its name is "+filenames[i]);
                    System.out.println("file["+i+"]'s "+"AbsolutePath is "+files[i].getAbsolutePath());
                }else {
                    fi = new FileItem(R.mipmap.file_icon,filenames[i],files[i].getAbsolutePath());
                    System.out.println("file["+i+"] is "+"file");
                }
                fileLists.add(fi);
                //String filename = helper.readFromSD(file.getAbsolutePath());
                //System.out.println(i+"fileName"+filename);
                // Toast.makeText(getApplicationContext(),filename,Toast.LENGTH_SHORT).show();
            }
            System.out.println("fileLists.length-"+fileLists.size());
            fileAdapter=new FileAdapter(getApplicationContext(),fileLists);
            fileListView.setAdapter(fileAdapter);
        }else{
            Toast.makeText(getApplicationContext(),"加载SD卡失败",Toast.LENGTH_SHORT).show();
        }
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePath.setText(lastDirectory);
                File file = new File(lastDirectory);
                String[] filenames = file.list();//
                File[] files = file.listFiles();//获得当前文件和文件夹
                lastDirectory=file.getParent();
                fileLists.clear();
                for (int i=0;i<filenames.length;i++){
                    FileItem fi;
                    if (files[i].isDirectory()) {
                        fi = new FileItem(R.mipmap.direcroty_icon, filenames[i],files[i].getAbsolutePath());
                        System.out.println("file["+i+"] is "+"directory");
                    }else {
                        fi = new FileItem(R.mipmap.file_icon,filenames[i],files[i].getAbsolutePath());
                        System.out.println("file["+i+"] is "+"file");
                    }
                    fileLists.add(fi);
                }
                fileAdapter.notifyDataSetChanged();
            }
        });


    }

    static class FileAdapter extends BaseAdapter{

        private Context context;
        private LinkedList<FileItem> filesList;

        public FileAdapter(Context context, LinkedList<FileItem> files) {
            this.context = context;
            this.filesList = files;
        }

        @Override
        public int getCount() {
            return filesList.size();
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
            convertView= LayoutInflater.from(this.context).inflate(R.layout.file_list_view_item_layout,parent,false);
            ImageView avatar=convertView.findViewById(R.id.image_file_avatar);
            TextView path=convertView.findViewById(R.id.text_view_file_path);

            avatar.setImageResource(filesList.get(position).getAvatar());
            path.setText(filesList.get(position).getFilename());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int length = 0;
                    String[] filenames;
                    File file = new File(filesList.get(position).getFilepath());
                    File[] files = file.listFiles();
                    filenames=file.list();
                    lastDirectory=file.getParent();
                    filePath.setText(file.getAbsolutePath());
                    fileLists.clear();
                    if (files==null){
                        length=0;
                    }else {
                        length=files.length;
                    }
                    for (int i = 0; i<length; i++){
                        FileItem fi;
                        if (files[i].isDirectory()) {
                            fi = new FileItem(R.mipmap.direcroty_icon, filenames[i], files[i].getAbsolutePath());
                            System.out.println("file["+i+"] is "+"directory");
                        }else {
                            fi = new FileItem(R.mipmap.file_icon,filenames[i], files[i].getAbsolutePath());
                            System.out.println("file["+i+"] is "+"file");
                        }
                        fileLists.add(fi);
                    }
                    fileAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }


    static class FileItem{
        private int avatar;
        private String filename;
        private String filepath;

        public FileItem(int avatar, String filename, String filepath) {
            this.avatar = avatar;
            this.filename = filename;
            this.filepath = filepath;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public int getAvatar() {
            return avatar;
        }

        public void setAvatar(int avatar) {
            this.avatar = avatar;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}