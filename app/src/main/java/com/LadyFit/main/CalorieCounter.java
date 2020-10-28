package com.LadyFit.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class CalorieCounter extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    String capHeight;
    String capWeight;
    String capMon;
    String capTues;
    String capWed;
    String capThurs;
    String capFri;
    String capSat;
    String capSun;

    Text height, weight, monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    FloatingActionButton logWeight;
    capturedData capData;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        myRef = database.getReference("Captured Data");

        // initiate a Switch
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchChange = (Switch) findViewById(R.id.switchChange);
        // check current state of a Switch (true or false).
        Boolean switchState = switchChange.isChecked();

        switchChange.setTextOn("Imperial"); // displayed text of the Switch whenever it is in checked or on state
        switchChange.setTextOff("Metric"); // displayed text of the Switch whenever it is in unchecked i.e. off state

        height = findViewById(R.id.etHeightNum);
        weight = findViewById(R.id.etWeightNum);
        monday = findViewById(R.id.txtSun);
        tuesday = findViewById(R.id.txtMon);
        wednesday = findViewById(R.id.txtTues);
        thursday = findViewById(R.id.txtWed);
        friday = findViewById(R.id.txtThurs);
        saturday = findViewById(R.id.txtFriday);
        sunday = findViewById(R.id.txtSat);
        logWeight = findViewById(R.id.btnLogWeight);

        logWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                capHeight = height.toString().trim();
                capWeight = weight.toString().trim();
                capMon = monday.toString().trim();
                capTues = tuesday.toString().trim();
                capWed = wednesday.toString().trim();
                capThurs = thursday.toString().trim();
                capFri = friday.toString().trim();
                capSat = saturday.toString().trim();
                capSun = sunday.toString().trim();

                capData = new capturedData(capHeight, capWeight, capMon, capTues, capWed, capThurs, capFri, capSat, capSun);
                myRef.push().setValue(capData)
                        .addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid)
                            {
                                Toast.makeText(CalorieCounter.this, "Weight has been successfully logged. ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(CalorieCounter.this, "Values cannot be empty. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
