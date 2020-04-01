package reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;

import com.example.trippalnner.R;


public class ReminderDialogActivity extends AppCompatActivity
//        implements ServiceConnection
{
//    ReminderSound.MyBinder soundBinder;
//    ReminderSound reminderSound;
//    Intent fireSound;
 MediaPlayer mp ;
 String tripName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        ReminderDialog dialog=new ReminderDialog();
        mp= MediaPlayer.create(this, R.raw.alarm);
//        fireSound =new Intent(this, ReminderSound.class);
//        bindService(fireSound,this, Service.BIND_AUTO_CREATE);
//        reminderSound.soundAlrm();
        Intent intent=getIntent();



//        LayoutInflater inflater = ReminderDialogActivity.this.getLayoutInflater();
//        View v = inflater.inflate(R.layout.dialog_layout, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(ReminderDialogActivity.this);
//        builder.setView(v);
//        builder.show();




//        ReminderDialog dialogFragment = new ReminderDialog();
        Bundle bundle = new Bundle();
        bundle.putString("tripName",intent.getStringExtra("tripName"));
        dialog.setArguments(bundle);
        dialog.show((ReminderDialogActivity.this).getSupportFragmentManager(),"Remonder Dialog");
tripName=intent.getStringExtra("tripName");


//        dialog.show(getSupportFragmentManager(),"Reminder Dialog");
        mp.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();

    }
    //    @Override
//    public void onServiceConnected(ComponentName name, IBinder binder) {
//    soundBinder= (ReminderSound.MyBinder) binder;
//    reminderSound = soundBinder.getService();
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//
//    }
}
