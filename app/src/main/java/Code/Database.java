package Code;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import POJOs.Note;
import POJOs.Trip;

public class Database {


    public Database() {
    }

    String recordId;

    public String getRecordId() {
        return recordId;
    }

    public  void addNoteToDataBase(Note note, String tripId){

        DatabaseReference databaseNote;
        databaseNote = FirebaseDatabase.getInstance().getReference("Note").child(tripId);
        String noteId = databaseNote.push().getKey();
        databaseNote.child(noteId).setValue(note);

    }


    public void addTripToDataBase(Context context, Trip trip) {

        DatabaseReference databaseTrip;
        databaseTrip = FirebaseDatabase.getInstance().getReference("Trip");
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

    public ArrayList<Trip> getTrips(){

        final ArrayList<Trip> tripArrayList = new ArrayList<>();

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference("Trip");

        tripDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){

                    Trip trip = dataSnapshot.getValue(Trip.class);
                    tripArrayList.add(trip);
                    //    Log.d("Debug", String.valueOf(tripArrayList.size()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return  tripArrayList;
    }

    //Return notes array list

    public ArrayList<Note> getآNotes(){

        final ArrayList<Note> noteArrayList = new ArrayList<>();

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference("Note");

        tripDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot tripSnapshot : dataSnapshot.getChildren()){

                    Note note = dataSnapshot.getValue(Note.class);
                    noteArrayList.add(note);
                    //    Log.d("Debug", String.valueOf(tripArrayList.size()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return  noteArrayList;
    }

    public boolean updateTrip(String id, Trip trip){

        DatabaseReference tripDatabase = FirebaseDatabase.getInstance().getReference("Trip").child(id);

        tripDatabase.setValue(trip);



        return true;
    }

    public boolean deleteTripAndNote(String id){
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference("Trip").child(id);
        DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference("Note").child(id);

        tripRef.removeValue();
        noteRef.removeValue();

        return true;
    }

}
