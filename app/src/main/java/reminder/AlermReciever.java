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
Database db=new Database();
    ArrayList<Trip> upcpmingTrips=new ArrayList<Trip>();
    String startDate;
    String startTime;
    String tripId;
    @Override
    public void onReceive(Context context, Intent intent) {
        startDate=intent.getStringExtra("tripDate");
        startTime=intent.getStringExtra("tripTime");
       upcpmingTrips= db.getUpCommingTrip();
       for(int i=0;i<upcpmingTrips.size();i++)
       {
           if(upcpmingTrips.get(i).getStartDate().equals(startDate)&&upcpmingTrips.get(i).getStartTime().equals(startTime))
           {
               tripId=upcpmingTrips.get(i).getId();
           }
       }
        Log.i("reciever","I Recieve");
        Intent reminder=new Intent(context, ReminderDialogActivity.class);
        reminder.putExtra("tripName",intent.getStringExtra("tripName"));
        reminder.putExtra("tripId",intent.getStringExtra("tripId"));

        reminder.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(reminder);
    }
}
