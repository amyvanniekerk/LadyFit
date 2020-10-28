package com.LadyFit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    String editName;
    String editEmail;
    String editUsername;
    String editPassword;
    String editAge;
    String editDob;

    EditText name, email, username, password, age, dob;
    Button btnSaveProfile = new Button (this);
    profileClass profileClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        myRef = database.getReference("Edit Profile");

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        age = findViewById(R.id.editTextAge);
        dob = findViewById(R.id.editTextDob);

        btnSaveProfile = (Button) findViewById(R.id.btnSaveProfile);
        btnSaveProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                editName = name.getText().toString().trim();
                editEmail = email.getText().toString().trim();
                editUsername = username.getText().toString().trim();
                editPassword = password.getText().toString().trim();
                editAge = age.getText().toString().trim();
                editDob = dob.getText().toString().trim();

                profileClass = new profileClass(editName, editEmail, editPassword, editAge, editDob);
                DatabaseReference myRef = database.getReference("Edit Profile");
                myRef.push().setValue(profileClass).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Toast.makeText(EditProfile.this, "Changes Saved.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditProfile.this, Options.class));
                    }
                    }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(EditProfile.this, "Values cannot be empty. Please enter again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

// btnSaveProfileClicked();
// private void btnSaveProfileClicked() {
             //
//   btnSaveProfile.setText("Changes Saved Successfully");
            //}

