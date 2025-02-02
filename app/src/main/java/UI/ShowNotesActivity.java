package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

import Adpters.ShowNotesAdapter;
import POJOs.Note;

public class ShowNotesActivity extends AppCompatActivity {
     String tripId;
    RecyclerView recyclerView;
    private ShowNotesAdapter adapter;
    ArrayList<Note> notesList;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        notesList = new ArrayList<>();
        tripId = getIntent().getStringExtra("tripId");
        mAuth= FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        recyclerView = findViewById(R.id.notes_rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       // DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Note");

        DatabaseReference tripRef=  FirebaseDatabase.getInstance().getReference(user.getUid()).child("Notes").child(tripId);
        Query selectQuery= tripRef.orderByChild("id").equalTo(tripId);
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesList.clear();

                for(DataSnapshot notesLists : dataSnapshot.getChildren()){
                    Note note = notesLists.getValue(Note.class);
                    notesList.add(note);
                    Log.d("Debug", note.getNote());
                }
                adapter = new ShowNotesAdapter(getApplicationContext(), notesList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    /*
    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    private void getData() {

        DatabaseReference tripRef= FirebaseDatabase.getInstance().getReference(user.getUid()).child("Notes");
        Query selectQuery= tripRef.orderByChild("id").equalTo(tripId);
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesList.clear();

                for(DataSnapshot notesLists : dataSnapshot.getChildren()){
                    Note note = notesLists.getValue(Note.class);
                       notesList.add(note);
                }
                adapter = new ShowNotesAdapter(notesList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

     */
}
