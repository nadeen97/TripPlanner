package com.example.trippalnner.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trippalnner.HistoryAdpater;
import com.example.trippalnner.HomeTripData;
import com.example.trippalnner.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<HomeTripData> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        System.out.println("From Home Fragment");
        recyclerView = root.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();

        //loading list view item with this function
        loadRecyclerViewItem();

        return root;
    }

    private void loadRecyclerViewItem() {


        for (int i = 1; i <= 5; i++) {
            HomeTripData myList = new HomeTripData(
                    "Dummy Trip title" + i,"ITI Alex Branch " ,"ITI Cairo Branch","25-3-2020","2:45AM","Trip Description hereee"
            );
            list.add(myList);
        }

        adapter = new HistoryAdpater(list,this.getContext());
        recyclerView.setAdapter(adapter);

    }
}
