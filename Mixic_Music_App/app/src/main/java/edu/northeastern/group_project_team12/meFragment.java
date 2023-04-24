package edu.northeastern.group_project_team12;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class meFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // Add a RecyclerView for displaying history records
    private RecyclerView mHistoryRecyclerView;
    // Add an ArrayAdapter for managing history records
    private HistoryAdapter mHistoryAdapter;
    // Add a List to store history records
    private List<String> mHistoryList = new ArrayList<>();

    public meFragment() {
    }
    public static meFragment newInstance(String param1, String param2) {
        meFragment fragment = new meFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab04, container, false);

        // Initialize the ArrayAdapter with a simple layout
        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the HistoryAdapter with the mHistoryList
        mHistoryAdapter = new HistoryAdapter(mHistoryList);

        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        // Fetch history records from the cloud and update the ListView
        fetchHistoryRecords();

        // Attach ItemTouchHelper for swipe-to-delete functionality
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(mHistoryAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(mHistoryRecyclerView);
        return view;
    }

    // Fetch history records
    private void fetchHistoryRecords() {
        // TODO: Implement the logic to fetch history records from the cloud
        // For now, adding dummy records to the list
        mHistoryList.add("Record 1");
        mHistoryList.add("Record 2");
        mHistoryList.add("Record 3");
        mHistoryAdapter.notifyDataSetChanged();
    }
}