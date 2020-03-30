package UI.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.Arrays;
import java.util.List;

import Adpters.HistoryAdpater;
import Adpters.HomeTripAdapter;
import POJOs.HomeTripData;
import POJOs.Trip;
import UI.HomeTripActivity;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryAdpater adapter;
    private ArrayList<Trip> list;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        System.out.println("History  Fragment");

        mAuth= FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        recyclerView = root.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return root;


    }

    private void getData() {
        list = new ArrayList<>();
        list.clear();

/*
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference citiesRef = db.collection("status");
        citiesRef.whereIn("status", Arrays.asList("cancel", "delete"));

*/
        DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Trip");
        Query selectQuery = tripRef.orderByChild("history").equalTo("true");


        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);

                }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity().getBaseContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                adapter = new HistoryAdpater(list,getContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
