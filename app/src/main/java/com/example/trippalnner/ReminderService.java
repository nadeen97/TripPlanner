package com.example.trippalnner;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class ReminderService extends IntentService {
public MyBinder myBinder=new MyBinder();
MyReceiver recv;

    public ReminderService()
    {
        super("ReminderService");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i("tag","Here I am");



    }

    //Binder
    public class  MyBinder extends Binder
    {
        ReminderService getService()
        {
            return  ReminderService.this;
        }
    }

    //Function you need for service doing
    public void testMethod()
    {
        Log.i("tag","Print form method in service");

        //broadcast
        IntentFilter filter=new IntentFilter("com.example.Broadcast");
        recv=new MyReceiver();
        registerReceiver(recv,filter);

        Intent myReciever=new Intent();
        myReciever.setAction("com.example.Broadcast");
        myReciever.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        MediaPlayer alermSound=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        alermSound.setLooping(true);

        alermSound.start();
        sendBroadcast(myReciever);
    }

}
