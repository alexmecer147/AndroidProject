package com.kanfeer.broadcastapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TheSecondReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        Bundle bd = getResultExtras(true);
       // System.out.println(bundle.isEmpty()+"kkkornnn");

        String smsg = bd.getString("next")+"第二个receiver也收到了";
        Toast.makeText(context,smsg,Toast.LENGTH_SHORT).show();
    }
}