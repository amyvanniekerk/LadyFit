package com.LadyFit.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    FirebaseAuth mAuth;
    Button btnSignup;
    EditText name, surname, email, username, createPass, verifyPass;
    String regName;
    String regSurname;
    String regEmail;
    String regUsername;
    String regCreatePass;
    String regVerifyPass;
    Signup signupDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        name = findViewById(R.id.etName);
        surname = findViewById(R.id.etSurname);
        email = findViewById(R.id.etEmail);
        username = findViewById(R.id.etUsername);
        createPass = findViewById(R.id.etCreatePass);
        verifyPass = findViewById(R.id.etVerifyPass);
        btnSignup =findViewById(R.id.btnSignup2);

        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] permissions =  {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

                regName = name.getText().toString().trim();
                regSurname = surname.getText().toString();
                regEmail = email.getText().toString().trim();
                regUsername = username.getText().toString().trim();
                regCreatePass = createPass.getText().toString().trim();
                regVerifyPass = verifyPass.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(regEmail, regCreatePass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Signup.this, "ERROR: " + e.getMessage() ,Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Signup.this, "Account Created." + mAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
                        profileClass p = new profileClass(regName + " " + regSurname,regEmail, "","",0,0);
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(p);
                        Intent i = new Intent(Signup.this,EditProfile.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}