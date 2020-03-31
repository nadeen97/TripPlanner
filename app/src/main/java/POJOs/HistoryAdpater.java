package POJOs;

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

public class HistoryAdpater  extends RecyclerView.Adapter<HistoryAdpater.ViewHolder> {

    private ArrayList<Trip> mylist;
    private Context mCtx;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseUser cUser=mAuth.getCurrentUser();
    public HistoryAdpater(ArrayList<Trip> list,Context context) {
        this.mylist = list;
        this.mCtx = context;
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
    public HistoryAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row,parent,false);
        HistoryAdpater.ViewHolder viewHolder = new HistoryAdpater.ViewHolder(item);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Trip myList = mylist.get(position);
        holder.tripName_tv.setText(myList.getTripName());
        holder.destination_tv.setText(myList.getDestinationString());
        holder.source_tv.setText(myList.getStartLocationString());
        holder.date_tv.setText(myList.getStartDate());
        holder.description_tv.setText(myList.getDescription());
        holder.statusTV.setText(myList.getStatus());
        holder.time.setText(myList.getStartTime());
        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo get notes
            }
        });
        holder.deleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 deleteTrip(myList,position);
            }
        });

        boolean isExpandable = myList.isExpandable();
        holder.expnadable.setVisibility(isExpandable? View.VISIBLE:View.GONE);

        holder.continar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.setExpandable(!myList.isExpandable());
                notifyItemChanged(position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tripName_tv, destination_tv,source_tv,date_tv ,statusTV,description_tv,time;
        LinearLayout expnadable,continar;
        Button deleteHistory, notes;


        public ViewHolder(View itemView) {
            super(itemView);
            tripName_tv = itemView.findViewById(R.id.tripName_tv);
            destination_tv = itemView.findViewById(R.id.dest_tv);
            source_tv = itemView.findViewById(R.id.src_tv);
            date_tv = itemView.findViewById(R.id.date_tv);
            description_tv = itemView.findViewById(R.id.desc);
            expnadable = itemView.findViewById(R.id.expandable_Layout);
            continar = itemView.findViewById(R.id.container_layout);
            statusTV = itemView.findViewById(R.id.state_tv);
            deleteHistory= itemView.findViewById(R.id.deletehist_btn);
            time =itemView.findViewById(R.id.time_his);
            notes = itemView.findViewById(R.id.show);


        }
    }
}
