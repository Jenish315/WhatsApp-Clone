package com.example.myapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.UserAdapter;
import com.example.myapplication.databinding.FragmentChatBinding;
import com.example.myapplication.models.users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatFragment extends Fragment {
    private FragmentChatBinding binding;
    private ArrayList<users> list = new ArrayList<>();
    private FirebaseDatabase database;

    public chatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();

        // Set up the adapter and RecyclerView
        UserAdapter adapter = new UserAdapter(list, getContext());
        binding.chatrecycleview.setAdapter(adapter);
        binding.chatrecycleview.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add ValueEventListener to the Firebase reference
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    users user = dataSnapshot.getValue(users.class);
                    if (user != null) {
                        user.setUserId(dataSnapshot.getKey());
                        Log.d("FirebaseData", "User: " + user.getUsername() + ", ID: " + user.getUserId());
                        list.add(user);
                    } else {
                        Log.d("FirebaseData", "User data is null");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle potential errors
                Log.e("FirebaseData", "Error: " + error.getMessage());
            }
        });

        return binding.getRoot();
    }
}
