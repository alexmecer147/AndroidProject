package com.kanfeer.thesdreadwriteapplication;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class SDFileHelper {
    private Context context;

    public SDFileHelper() {
    }

    public SDFileHelper(Context context) {
        super();
        this.context = context;
    }
    // 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    //获取SD卡根目录
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            //getAbsolutePath() 方法返回文件的绝对路径，如果构造的时候是全路径就直接返回全路径，
            // 如果构造时是相对路径，就返回当前目录的路径 + 构造 File 对象时的路径
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }
    //往SD卡写入文件的方法
    public void saveFileToSD(String filename, String filecontent) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限，则Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)返回true
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //if(isSdCardExist()){
            //getCanonicalPath() 方法返回绝对路径，会把 ..\ 、.\ 这样的符号解析掉
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
//           File sdCardDir=Environment.getExternalStorageDirectory();
//            File saveFile=new File(sdCardDir,filename);
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream output = new FileOutputStream(filename);
//            FileOutputStream output = new FileOutputStream(saveFile);
            //将String字符串以字节流的形式写入到输出流中
            output.write(filecontent.getBytes());
            //关闭输出流
            output.close();
        } else
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        //Android应用开发中，常使用Environment类去获取外部存储目录，
        // 在访问外部存储之前一定要先判断外部存储是否已经是可使用（已挂载&可使用）状态,
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];
            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }
}
