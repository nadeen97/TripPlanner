package reminder;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Code.Database;
import POJOs.Trip;

public class AuthenticationCheckService extends IntentService {
    Database db = new Database();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    ArrayList<Trip> upcpmingTrips = new ArrayList<Trip>();

    public AuthenticationCheckService() {
        super("AuthenticationCheckService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Once this code runs and its done the service stops so need to worry about long running service
        Log.i("trip", "Service starteddd");
        checkAuthenticationStatus();
    }

    private void checkAuthenticationStatus() {
        Log.i("trip", "in method service");

//        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(FirebaseAuth firebaseAuth)  {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    //Do stuffs
//                    upcpmingTrips= db.getUpCommingTrip();
//                    for(int i=0;i<upcpmingTrips.size();i++)
//                    {
//                        Log.i("trip name",upcpmingTrips.get(i).getTripName());
////           if(upcpmingTrips.get(i).getStartDate().equals(startDate)&&upcpmingTrips.get(i).getStartTime().equals(startTime))
////           {
////               tripId=upcpmingTrips.get(i).getId();
////           }
//                    }
//                } else {
//                    Log.i("trip","User nulllllll");
//                    //Resolve to authentication activity
//                }
//            }
//        });

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Log.i("trip ", "mAuth " + mAuth);
        Log.i("trip ", "mUser " + user);
//        upcpmingTrips= db.getUpCommingTrip();
//                    for(int i=0;i<upcpmingTrips.size();i++) {
//                        Log.i("trip name", upcpmingTrips.get(i).getTripName());
//                    }


        final ArrayList<Trip> list = new ArrayList<>();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(user.getUid()).child("Trip");
        ///todo problem here
        Query selectQuery= tripRef.orderByChild("status").equalTo("upComing");
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        for (int i = 0; i < list.size(); i++) {
            Log.i("trip name", list.get(i).getTripName());
//                    }
        }
//        Log.i("test",list.get(0).getStartTime());

    }
}

