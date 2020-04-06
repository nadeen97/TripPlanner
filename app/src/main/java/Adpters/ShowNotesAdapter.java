package Adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trip.trippalnner.R;

import java.util.ArrayList;

import POJOs.Note;

public class ShowNotesAdapter extends RecyclerView.Adapter<ShowNotesAdapter.ViewHolder> {
    ArrayList<Note> notes = new ArrayList<>();

    Context context;

        public ShowNotesAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.show_notes_item, parent, false);


        return new ViewHolder(v);
/*
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_notes_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

 */
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        //todo complete task
             //   holder.checkBox.setChecked(currentNote.isChecked());
                holder.noteName_tv.setText(currentNote.getNote());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteName_tv;
       // CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            noteName_tv= itemView.findViewById(R.id.tv_show_note);
           // checkBox= itemView.findViewById(R.id.checkBox_select);

        }
    }
}
