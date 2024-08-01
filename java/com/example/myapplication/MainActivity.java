package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Adapter.FragmetsAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewpage2.setAdapter(new FragmetsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewpage2);
        binding.tabLayout.setElevation(0);

    }

    //

    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setting_item) {
            // Handle settings item click
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            // Add your settings action here
            return true;
        } else if (id == R.id.logout_item) {
            // Handle logout item click
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
            // Perform logout action and navigate to sign-in activity
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, signin_activity.class);
            startActivity(intent);
            finish(); // Finish current activity to prevent going back
            return true;
        } else if (id == R.id.room_Chat) {
            Intent intent = new Intent(MainActivity.this, chat_room.class);
            startActivity(intent);


        } else {
            return super.onOptionsItemSelected(item);
        }
        return false;
    }

}



