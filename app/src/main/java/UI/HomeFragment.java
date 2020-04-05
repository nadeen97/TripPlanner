package UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.trip.trippalnner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import POJOs.HomeTripAdapter;
import POJOs.Trip;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private HomeTripAdapter adapter;
    private ArrayList<Trip> list= new ArrayList<>();
    private FirebaseAuth mAuth;
     private FirebaseUser user;
     ImageView img;
     double avgDistance;
     double totalDistance;
     int counter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        avgDistance = 0.0;
        totalDistance = 0.0;
        counter = 0;
        View root = inflater.inflate(R.layout.fragment_home, container, false);
         mAuth= FirebaseAuth.getInstance();
         user=mAuth.getCurrentUser();
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
              return root;
    }


   private void getData() {
        list = new ArrayList<>();
        list.clear();

        DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Trip");
        Query selectQuery= tripRef.orderByChild("status").equalTo("upComing");
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);
                    //TODO average calculation
                    if (trip.getDistance()!=null){


                    String distance = (trip.getDistance().replace("km", ""))
                                        .replace(",", "").replace(".","").trim(); //"220 km"


                      // Log.d("Debug", distance);

                        double dist;
                       if (!distance.isEmpty()){

                     dist = Double.parseDouble(distance);
                       }else {
                           dist=0.0;
                       }

                   // Integer.getInteger();
                    totalDistance += dist;
                    counter++;
                    }

                }

                if (avgDistance!=0.0 && counter!=0){

                avgDistance = totalDistance/counter;

               // Log.d("Debug", String.valueOf(avgDistance));
                }
                adapter = new HomeTripAdapter(list,getActivity());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    /*
Database database = new Database();

   private void getData() {
      list = database.getTrips();
       adapter = new HomeTripAdapter(list,getContext());
       recyclerView.setAdapter(adapter);
   }*/
}
