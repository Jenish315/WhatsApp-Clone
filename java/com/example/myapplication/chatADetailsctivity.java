package com.example.myapplication;

import android.os.Bundle;
import android.os.Message;
import android.view.WindowInsets;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.myapplication.Adapter.chatAdapter;
import com.example.myapplication.databinding.ActivityChatAdetailsctivityBinding;
import com.example.myapplication.models.messagesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatADetailsctivity extends AppCompatActivity {
ActivityChatAdetailsctivityBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_adetailsctivity);

        View mainLayout = findViewById(R.id.main);

        mainLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                int bottomInset = insets.getSystemWindowInsetBottom();
                v.setPadding(0, 0, 0, bottomInset);
                return insets;
            }
        });

        getSupportActionBar().hide();

        binding = ActivityChatAdetailsctivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
       final String senderId =auth.getUid();
        String recieveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profile_pic");

        binding.userChatId.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.man_user_circle_icon).into(binding.profileImage);


        final ArrayList<messagesModel>messagesModels = new ArrayList<>();
        final chatAdapter chatAdapter = new chatAdapter(messagesModels,this);
        binding.chatrecycleview.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         binding.chatrecycleview.setLayoutManager(layoutManager);

     final String senderRoom = senderId + recieveId;
final String receiverRoom = recieveId+ senderId;

         binding.sendmessageicon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              String message =   binding.sendmessage.getText().toString();
              final  messagesModel  model= new messagesModel(senderId,message);
              model.setTimestamp(new Date().getTime());
              binding.sendmessage.setText("");
              database.getReference().child("chats").child(senderRoom).push()
                      .setValue(model)
                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void unused) {
database.getReference().child("chats").child(receiverRoom).push()
        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
                          }
                      });
             }
         });

         database.getReference().child("chats").child(senderRoom)
                 .addValueEventListener(new ValueEventListener() {
                     @Override


                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                   messagesModels.clear();
                         for(DataSnapshot snapshot1: snapshot.getChildren()){
messagesModel model = snapshot1.getValue(messagesModel.class);
    messagesModels.add(model);
chatAdapter.notifyDataSetChanged();

                         }

                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                     }
                 });



    }

}