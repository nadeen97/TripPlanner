package reminder;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;


public class ReminderSound extends IntentService {
    public MyBinder myBinder=new MyBinder();
    MediaPlayer alermSound=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

    public ReminderSound()
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
        ReminderSound getService()
        {
            return  ReminderSound.this;
        }
    }

    //Function you need for service doing
    public void soundAlrm()
    {
        Log.i("tag","Print form method in service");

        alermSound.setLooping(true);

        alermSound.start();
    }
}
