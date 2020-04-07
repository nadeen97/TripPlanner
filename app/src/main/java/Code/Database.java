package Code;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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

    //public  void addNoteToDataBase(Note note, String tripId,String uid){

    public void addNoteToDataBase(Note note, String tripId) {

        DatabaseReference databaseNote;
        // databaseNote = FirebaseDatabase.getInstance().getReference(uid).child("Notes").child(tripId);




        databaseNote = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Notes").child(tripId);

        String noteId = databaseNote.push().getKey();
        note.setRelatedTripId(noteId);
        databaseNote.child(noteId).setValue(note);

    }


    public void addTripToDataBase(Context context, Trip trip) {

        DatabaseReference databaseTrip;
        databaseTrip = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        //generate a unique id and save the value in it.

        if (trip.getId().isEmpty()){

        String DBId = databaseTrip.push().getKey();
        //
        recordId = DBId;
        //
        trip.setId(DBId);
        databaseTrip.child(DBId).setValue(trip);
        }else {

        databaseTrip.child(trip.getId()).setValue(trip);

        }


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


    public boolean deleteNote(String id) {
        DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference("Note").child(id);

        noteRef.removeValue();

        return true;
    }

    /////////////////////////////////////
    public ArrayList<Trip> getUpCommingTrip() {
        final ArrayList<Trip> list = new ArrayList<>();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip");
        ///todo problem here
        Query selectQuery = tripRef.orderByChild("status").equalTo("Upcoming");
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

    public void deleteTrip(final Trip tripToDelete, final Context context){


        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Trip")
                .setMessage("Are You Sure You want to delete this trip?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(tripToDelete.getId())
                                .child("status").setValue("Deleted");
                        FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(tripToDelete.getId())
                                .child("history").setValue("false");

                        Toasting.toastAnywhere(context,"Trip deleted successfully",0);
                        ((Activity)context).finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toasting.toastAnywhere(context,"Cancelled",0);


                    }
                })
                .show();
    }

}
