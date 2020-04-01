package reminder;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import Code.Database;
import POJOs.Trip;

public class AuthenticationCheckService extends IntentService {
    Database db=new Database();

    ArrayList<Trip> upcpmingTrips=new ArrayList<Trip>();
    public AuthenticationCheckService() {
        super("AuthenticationCheckService");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        //Once this code runs and its done the service stops so need to worry about long running service
        checkAuthenticationStatus();
    }

    private void checkAuthenticationStatus() {
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth)  {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Do stuffs
                    upcpmingTrips= db.getUpCommingTrip();
                    for(int i=0;i<upcpmingTrips.size();i++)
                    {
                        Log.i("trip name",upcpmingTrips.get(i).getTripName());
//           if(upcpmingTrips.get(i).getStartDate().equals(startDate)&&upcpmingTrips.get(i).getStartTime().equals(startTime))
//           {
//               tripId=upcpmingTrips.get(i).getId();
//           }
                    }
                } else {
                    //Resolve to authentication activity
                }
            }
        });
    }
}

