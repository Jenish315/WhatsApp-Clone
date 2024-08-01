package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database; // Declare FirebaseDatabase variable
    private static final String TAG = "Signup"; // Define TAG constant
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance(); // Corrected method name
        progressDialog = new ProgressDialog(Signup.this);

        progressDialog.setTitle("Creating user");
        progressDialog.setMessage("wait for while");
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if (TextUtils.isEmpty(binding.etemail.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(binding.etpassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(binding.etemail.getText().toString(), binding.etpassword.getText().toString())
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                            progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");

                                    Toast.makeText(Signup.this, "Account created sucessfully , please login with your new account", Toast.LENGTH_SHORT).show();

                                    // Store user data in the Realtime Database
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userId = user.getUid();
                                    String username = binding.etusername.getText().toString();
                                    String email = binding.etemail.getText().toString();

                                    // Define a reference to the "Users" node and set user data under the user ID
                                    database.getReference().child("Users").child(userId).child("username").setValue(username);
                                    database.getReference().child("Users").child(userId).child("email").setValue(email);
Intent intent = new Intent(Signup.this,signin_activity.class);
startActivity(intent);

                                    // You can add more user data as needed

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
        binding.tvhaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,signin_activity.class);
                startActivity(intent);
            }
        });

    }


    private void updateUI(FirebaseUser user) {
        // You can add your UI update logic here
    }

}
