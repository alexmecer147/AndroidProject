package com.kanfeer.broadcastapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TheFirstReceiver extends BroadcastReceiver {

    //private static final String TAG = "TheFirstReceiver";

    //private String ACTION = "broadcastapplication.action.SEND_MASSAGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String msg = intent.getStringExtra("msg")+"第一个broadcast修改了";
       // System.out.println(msg+"9999");
        //Log.i(TAG,msg);
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}