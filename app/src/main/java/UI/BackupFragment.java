package UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import Adpters.BackupAdapter;
import Adpters.HomeTripAdapter;
import POJOs.Trip;


public class BackupFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BackupAdapter adapter;
    private ArrayList<Trip> list= new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_backup, container, false);
        mAuth= FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        recyclerView = root.findViewById(R.id.rec_bk);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return root;
    }


    private void getData() {
        list = new ArrayList<>();
        list.clear();

        DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Trip");
        Query selectQuery= tripRef.orderByChild("status").equalTo("Deleted");
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);

                }
                adapter = new BackupAdapter(list,getContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
