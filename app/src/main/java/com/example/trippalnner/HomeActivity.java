package com.example.trippalnner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth =FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {
            Intent goToLogin = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(goToLogin);

        }
        if(mAuth.getCurrentUser()!=null)
        {
           Toast.makeText(HomeActivity.this,mAuth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();

        }
//        Intent Reminder=new Intent(this, ReminderActivity.class);
//        startActivity(Reminder);
    }
}
