package com.LadyFit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Options extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    Button logWeight, setGoals, capMeals, editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        myRef = database.getReference("Options");

       // logWeight = findViewById(R.id.btnLogWeight);
       // setGoals = findViewById(R.id.btnSetGoals);
       // capMeals = findViewById(R.id.btnCapMeals);
       // editProfile = findViewById(R.id.btnEditProfile);

        logWeight = (Button) findViewById(R.id.btnLogWeight);
        logWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, CalorieCounter.class));
            }
        });

        setGoals = (Button) findViewById(R.id.btnSetGoals);
        setGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, CalorieCounter.class));
            }
        });

        capMeals = (Button) findViewById(R.id.btnCapMeals);
        capMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, CalorieCounter.class));
            }
        });

        editProfile = (Button) findViewById(R.id.btnEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, EditProfile.class));
            }
        });
    }
}