package reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.example.trippalnner.R;


public class ReminderDialogActivity extends AppCompatActivity implements ServiceConnection {
    ReminderSound.MyBinder soundBinder;
    ReminderSound reminderSound;
    Intent fireSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        ReminderDialog dialog=new ReminderDialog();
//        fireSound =new Intent(this, ReminderSound.class);
//        bindService(fireSound,this, Service.BIND_AUTO_CREATE);
//        reminderSound.soundAlrm();
        dialog.show(getSupportFragmentManager(),"Reminder Dialog");

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
    soundBinder= (ReminderSound.MyBinder) binder;
    reminderSound = soundBinder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
