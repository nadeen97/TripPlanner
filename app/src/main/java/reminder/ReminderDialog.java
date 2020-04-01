package reminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ReminderDialog extends AppCompatDialogFragment {
    ReminderDialogActivity rm= (ReminderDialogActivity) getActivity();
//    private FirebaseUser cUser=mAuth.getCurrentUser();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String tripName = bundle.getString("tripName","Your Trip");
        //here we should change getActivity
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());


        //shape of dialog
        //you should cahnnge it to trip information
        builder.setTitle(tripName)
                .setTitle("It's Time for  "+ tripName)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //update trip in db to cancel
//
                        rm.mp.stop();
                    }
                })
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //update trip in db to done

                        rm.mp.stop();

                    }
                })
        .setNeutralButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NotificationHelper notificationHelper = new NotificationHelper(getContext());

                NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                notificationHelper.getManager().notify(1, nb.build());
                rm.mp.stop();

            }
        });
        Log.i("trip",tripName);
        return  builder.create();
    }
}
