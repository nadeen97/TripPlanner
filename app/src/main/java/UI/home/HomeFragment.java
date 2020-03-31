package UI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trippalnner.R;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Home  ");

        View root = inflater.inflate(R.layout.fragment_home, container, false);
         mAuth= FirebaseAuth.getInstance();
         user=mAuth.getCurrentUser();

        recyclerView = root.findViewById(R.id.recycler);
        img= root.findViewById(R.id.imageView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData();
        checkVisibility();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


              return root;
    }


   private void getData() {
        list = new ArrayList<>();
        list.clear();

        DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Trip");
        Query selectQuery= tripRef.orderByChild("status").equalTo("Upcoming");
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);

                }
                adapter = new HomeTripAdapter(list,getActivity());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    void checkVisibility(){
        if (!list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
        }
    }
/*
Database database = new Database();

   private void getData() {
      list = database.getTrips();
       adapter = new HomeTripAdapter(list,getContext());
       recyclerView.setAdapter(adapter);
   }*/
}
