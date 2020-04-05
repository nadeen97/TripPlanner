package com.trip;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class TripPlanner extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
