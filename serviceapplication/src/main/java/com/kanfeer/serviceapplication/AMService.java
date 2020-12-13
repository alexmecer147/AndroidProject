package com.kanfeer.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class AMService extends Service {
    int count = 0;
    @Override
    public void onCreate(){
        super.onCreate();
        System.out.println("onCreate");
        new Thread(){
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        AMBinder amBinder = new AMBinder();
        return amBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public class AMBinder extends Binder {
        public int multiplicate(int a){
            int mul = 1;
            for (int i = 1; i<=a;i++){
                mul *= i;
            }
            return mul;
        }
        
    }
    private int sum = 0;
    public int accumulate(int a){
        sum = 0;
        for (int i = 1; i <= a;i++){
            sum += i;
        }
        return sum;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }
}
