package UI.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trippalnner.HomeActivity;
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
import java.util.List;

import Adpters.HomeTripAdapter;
import Code.Database;
import POJOs.HomeTripData;
import POJOs.Trip;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private HomeTripAdapter adapter;
    private ArrayList<Trip> list= new ArrayList<>();
    private FirebaseAuth mAuth;
     private FirebaseUser user;

/*
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PersonAdapter mAdapter = new PersonAdapter(Arrays.asList(myListData));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
         */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Home  ");

        View root = inflater.inflate(R.layout.fragment_home, container, false);
         mAuth= FirebaseAuth.getInstance();
         user=mAuth.getCurrentUser();

        recyclerView = root.findViewById(R.id.home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*HomeActivity mAdapter = new HomeActivity(Arrays.asList(myListData));
        recyclerView.setAdapter(mAdapter);*/
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
