package Adpters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trippalnner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import POJOs.Trip;

public class BackupAdapter extends   RecyclerView.Adapter   <BackupAdapter.ViewHolder> {

    private ArrayList<Trip> trips;
    private Context mCtx;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser cUser = mAuth.getCurrentUser();

    public BackupAdapter(ArrayList<Trip> t, Context context) {
        this.mCtx = context;
        this.trips = t;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.backup_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Trip currentItem = trips.get(position);
        holder.tripName_tv.setText(currentItem.getTripName());
        holder.date_tv.setText(currentItem.getStartDate());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTrip(currentItem,position);
            }
        });

        holder.undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(currentItem.getId())
                        .child("status").setValue("Upcoming");

            }
        });
    }


    void deleteTrip(final Trip currentItem, final int position) {


        AlertDialog alertDialog = new AlertDialog.Builder(mCtx)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Trip")
                .setMessage("Are You Sure You?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String id = currentItem.getId();
                        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(id);
                        DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Note").child(id);
                        tripRef.removeValue();
                        noteRef.removeValue();
                        notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tripName_tv, date_tv;
        Button delete, undo;


        public ViewHolder(View itemView) {
            super(itemView);
            tripName_tv = itemView.findViewById(R.id.trip_name_bk);
            date_tv = itemView.findViewById(R.id.date_bk);
            delete = itemView.findViewById(R.id.delete_btn);
           undo = itemView.findViewById(R.id.undo_btn);

        }
    }
}