package POJOs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trippalnner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

import UI.NotesActivity;
import UI.UpdateActivity;

public class HomeTripAdapter extends RecyclerView.Adapter<HomeTripAdapter.ViewHolder> {

    private ArrayList<Trip> trips;
    private Context mCtx;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseUser cUser=mAuth.getCurrentUser();

    public HomeTripAdapter(ArrayList<Trip> t,Context context) {
        this.mCtx = context;
        this.trips=t;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;

    }


    void deleteTrip(final Trip currentItem, final int position){


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
    public void onBindViewHolder(final HomeTripAdapter.ViewHolder holder, final int position) {
        final Trip currentItem = trips.get(position);
        holder.tripName_tv.setText(currentItem.getTripName());
        holder.destination_tv.setText(currentItem.getDestinationString());
        holder.source_tv.setText(currentItem.getStartLocationString());
        holder.time_tv.setText(currentItem.getStartTime());
        holder.date_tv.setText(currentItem.getStartDate());
      holder.description_tv.setText(currentItem.getDescription());
        boolean isExpandable = currentItem.isExpandable;
        holder.expnadable.setVisibility(isExpandable? View.VISIBLE:View.GONE);

        holder.continar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setExpandable(!currentItem.isExpandable());
                notifyItemChanged(position);
            }
        });



        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mCtx, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.card_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.notes:
                                Intent intent = new Intent(mCtx, NotesActivity.class);
                                mCtx.startActivity(intent);
                                break;
                            case R.id.edit:
                   // TODO: 3/30/2020  to update
                               Intent intentUpdate=new Intent(mCtx, UpdateActivity.class);
                                intentUpdate.putExtra("trip",currentItem);
                                mCtx.startActivity(intentUpdate);
                               // in other intent


                                break;
                            case R.id.delete:
                                FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(currentItem.getId())
                                        .child("status").setValue("Deleted");

                                FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(currentItem.getId())
                                        .child("history").setValue("false");
                                break;
                            case R.id.cancel:
                                FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(currentItem.getId())
                                        .child("status").setValue("Canceled");
                                FirebaseDatabase.getInstance().getReference(cUser.getUid()).child("Trip").child(currentItem.getId())
                                        .child("history").setValue("true");

                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tripName_tv, destination_tv,source_tv,time_tv,date_tv ,buttonViewOption,description_tv;
        LinearLayout continar;
         View expnadable;

        public ViewHolder(View itemView) {
            super(itemView);
            tripName_tv = itemView.findViewById(R.id.tripName_tv);
            destination_tv = itemView.findViewById(R.id.dest_tv);
            source_tv = itemView.findViewById(R.id.src_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            date_tv = itemView.findViewById(R.id.date_tv);
          description_tv = itemView.findViewById(R.id.desc);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
            expnadable = itemView.findViewById(R.id.expandable_Layout);
            continar = itemView.findViewById(R.id.container_layout);

        }
    }
}
