package reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;

import Code.Database;
import POJOs.Trip;

public class AlermReciever extends BroadcastReceiver {

    String startDate;
    String startTime;
    String tripId;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("reciever","I Recieve");
//        Intent authCheckIntent = new Intent(context, AuthenticationCheckService.class);
////        context.startService(authCheckIntent);
//        AuthenticationCheckService.enqueueWork(context, new Intent());

//        checkAuthStatus(context);
        startDate=intent.getStringExtra("tripDate");
        startTime=intent.getStringExtra("tripTime");


//       Log.i("t",tripId);
        Log.i("trip",startDate + startTime);
        Intent reminder=new Intent(context, ReminderDialogActivity.class);
        reminder.putExtra("tripName",intent.getStringExtra("tripName"));
        reminder.putExtra("tripId",tripId);

        reminder.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(reminder);
    }

    private void checkAuthStatus(Context context) {
        Intent authCheckIntent = new Intent(context, AuthenticationCheckService.class);
        context.startService(authCheckIntent);
    }
}
