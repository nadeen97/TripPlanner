package com.example.trippalnner;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderActivity extends AppCompatActivity  implements ServiceConnection //Rmember here implements
{
    //refrence to my binder
    ReminderService.MyBinder myBinderObj;
    //refrence to my service
    ReminderService myServics;
    //refrence to intent to your service
    Intent fireAlram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

//val of im=intent
        fireAlram=new Intent(ReminderActivity.this, ReminderService.class);
        bindService(fireAlram,this, Service.BIND_AUTO_CREATE);
        Button b=findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myServics.testMethod();

            }
        });



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
