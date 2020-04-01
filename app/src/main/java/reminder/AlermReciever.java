package reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class AlermReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("reciever","I Recieve");
        Intent reminder=new Intent(context, ReminderDialogActivity.class);
        reminder.putExtra("tripName",intent.getStringExtra("tripName"));
        reminder.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(reminder);
    }
}
