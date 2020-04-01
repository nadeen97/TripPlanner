package reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.trippalnner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import Code.MapLauncher;


public class ReminderDialogActivity extends AppCompatActivity
{

 MediaPlayer mp ;
Intent intent;
    AlertDialog.Builder builder;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    private FirebaseUser cUser=mAuth.getCurrentUser();

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("dialog","onrestarttt()");

        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);

        mp= MediaPlayer.create(this, R.raw.alarm);
        mp.start();
        builder=new AlertDialog.Builder(ReminderDialogActivity.this);
         intent=getIntent();
        builder.setTitle(intent.getStringExtra("tripName"))
                .setTitle("It's Time for  "+intent.getStringExtra("tripName") )
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //update trip in db to cancel
//                        FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(intent.getStringExtra("tripId"))
//                                .child("status").setValue("Canceled");
//                        FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(intent.getStringExtra("tripId"))
//                                .child("history").setValue("true");
                        dialog.dismiss();
                        finish();
                        mp.stop();
                    }
                })
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //update trip in db to done
                        MapLauncher ml=new MapLauncher();
                        ml.launchMapsFromCurrentLocation(ReminderDialogActivity.this,intent.getStringExtra("tripDest"));
                        dialog.dismiss();
                        finish();
                        mp.stop();

                    }
                })
                .setNeutralButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        NotificationHelper notificationHelper = new NotificationHelper(ReminderDialogActivity.this);
//
//                        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//                        notificationHelper.getManager().notify(1, nb.build());
                        Intent notficationInetent=new Intent(ReminderDialogActivity.this,NotificationService.class);
                        notficationInetent.putExtra("tripName",intent.getStringExtra("tripName") );
                        startService(notficationInetent);
                        dialog.dismiss();
                        finish();
                        mp.stop();

                    }
                });
        builder.show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("dialog","onStart()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("dialog","onstop()");

        mp.stop();

    }

}
