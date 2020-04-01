package Code;

import android.content.Context;

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

import POJOs.Note;
import POJOs.Trip;

public class Database {

    // private FirebaseAuth mAuth;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser cUser = mAuth.getCurrentUser();

    public Database() {

    }

    String recordId;

    public String getRecordId() {
        return recordId;
    }

    public void addNoteToDataBase(Note note, String tripId, String uid) {

        DatabaseReference databaseNote;
        databaseNote = FirebaseDatabase.getInstance().getReference(uid).child("Notes").child(tripId);
        String noteId = databaseNote.push().getKey();
        databaseNote.child(noteId).setValue(note);

    }


    public void addTripToDataBase(Context context, Trip trip) {

        DatabaseReference databaseTrip;
        databaseTrip = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        //generate a unique id and save the value in it.
        String DBId = databaseTrip.push().getKey();
        //
        recordId = DBId;
        //
        trip.setId(DBId);
        databaseTrip.child(DBId).setValue(trip);

//        Toast toast = Toast.makeText(context, "Trip Added successfully", Toast.LENGTH_SHORT);
        //      toast.show();


    }

    public ArrayList<Trip> getTrips() {
/*
        final ArrayList<Trip> tripArrayList = new ArrayList<>();

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference(uid).child("Trip");

        tripDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){

                    Trip trip = tripSnapshot.getValue(Trip.class);
                    tripArrayList.add(trip);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        final ArrayList list = new ArrayList<>();


        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        Query selectQuery = tripRef.orderByChild("status").equalTo("upComing");
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
        return list;
    }

    //Return notes array list


    public ArrayList<Note> getآNotes(String uid) {

        final ArrayList<Note> noteArrayList = new ArrayList<>();

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference(uid).child("Notes");

        tripDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {

                    Note note = dataSnapshot.getValue(Note.class);
                    noteArrayList.add(note);
                    //    Log.d("Debug", String.valueOf(tripArrayList.size()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return noteArrayList;
    }


    public boolean updateTrip(String id, Trip trip) {

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference("Trip").child(id);

        tripDatabase.setValue(trip);


        return true;
    }

    public boolean deleteTripAndNote(String id) {
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference("Trip").child(id);
        DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference("Note").child(id);

        tripRef.removeValue();
        noteRef.removeValue();

        return true;
    }

    /////////////////////////////////////
    public ArrayList<Trip> getUpCommingTrip() {
        final ArrayList<Trip> list = new ArrayList<>();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        ///todo problem here
        Query selectQuery = tripRef.orderByChild("status").equalTo("upComing");
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
        return list;
    }

    public ArrayList<Trip> getUpHistoryTrip() {
        final ArrayList<Trip> list = new ArrayList<>();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        Query selectQuery = tripRef.orderByChild("history").equalTo("true");

        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    list.add(trip);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}





