package com.example.trippalnner;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class ReminderBroadcast extends BroadcastReceiver implements ServiceConnection {

    //refrence to my binder
    ReminderService.MyBinder myBinderObj;
    //refrence to my service
    ReminderService myServics;
    //refrence to intent to your service
    Intent fireAlram;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("start","I recieve from start broadcast");
        fireAlram=new Intent(context, ReminderService.class);
        context.bindService(fireAlram,this, Service.BIND_AUTO_CREATE);

        context.startService(fireAlram);
        myServics.testMethod();

    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder mybinder) {
        myBinderObj= (ReminderService.MyBinder) mybinder;
        myServics=myBinderObj.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
