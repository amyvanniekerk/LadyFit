package com.LadyFit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private FirebaseAuth mAuth;
    Button signup;
    Button login;
    String stEmail;
    String stPassword;
    EditText email, password;
    TextView errorMessage;
    LoginDetails loginDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRef = database.getReference("Login");

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        // linking buttons to main activity button
        signup = findViewById(R.id.btnSignup);
        login = findViewById(R.id.btnLogin);

        // Initialize Firebase Auth
       // final Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail, etPassword);
        mAuth = FirebaseAuth.getInstance();
        signup = (Button) findViewById(R.id.btnSignup);

        // --- Code Attribution. This code was taken from, https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity
        // sign up button is clicked
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
        // login button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stEmail = email.toString().trim();
                stPassword = password.toString().trim();

                mAuth.signInWithEmailAndPassword(stEmail, stPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User " + mAuth.getCurrentUser().getEmail()
                                    + " successfully logged in.", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(MainActivity.this, Options.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                    //End of Attribution ---
                });
            }
        });
    }
}
