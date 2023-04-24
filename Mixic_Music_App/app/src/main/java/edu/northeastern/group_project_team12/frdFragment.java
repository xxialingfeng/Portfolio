package edu.northeastern.group_project_team12;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class frdFragment extends Fragment {

    // Add a member variable for the RecyclerView and the FriendAdapter
    private RecyclerView friendsRecyclerView;
    private FriendAdapter friendAdapter;

    // ...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab02, container, false);

        // Find the RecyclerView in the layout
        friendsRecyclerView = view.findViewById(R.id.friends_recyclerview);

        // Set up the RecyclerView with a LinearLayoutManager and the FriendAdapter
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        friendAdapter = new FriendAdapter(getActivity(), getFriends());
        friendsRecyclerView.setAdapter(friendAdapter);

        return view;
    }

    private List<Friend> getFriends() {
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Alice", "https://example.com/alice.jpg"));
        friends.add(new Friend("Bob", "https://example.com/bob.jpg"));
        friends.add(new Friend("Carol", "https://example.com/carol.jpg"));
        // Add more friends to the list
        return friends;
    }
}