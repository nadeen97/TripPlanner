package com.example.trippalnner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_reminder);
        ReminderDialog dialog=new ReminderDialog();


        dialog.show(getSupportFragmentManager(),"Reminder Dialog");
    }
}
