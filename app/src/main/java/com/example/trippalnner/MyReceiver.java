package com.example.trippalnner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("from broadcastReciever","I recieve");

Intent reminder=new Intent(context, ReminderDialogActivity.class);
        reminder.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(reminder);

    }
}
