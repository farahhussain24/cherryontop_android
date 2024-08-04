package com.example.cherryontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText regEmail;
    EditText regUserName;
    EditText regPassword;

    private FirebaseAuth auth;
    FirebaseDatabase userCredentialsDatabase;


    // for loading bubble symbol
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //        Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();

        // Initialize Firebase Auth

        auth = FirebaseAuth.getInstance();

//        Initialize firebase database instance



        userCredentialsDatabase = FirebaseDatabase.getInstance();

//        for progress dialog
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Your account is being created.");
    }

    public void createAccount(View view) {
        //starting the progress dialog
        progressDialog.show();
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regUserName = findViewById(R.id.regUserName);

        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String userName = regUserName.getText().toString();

        if (email.isEmpty() || password.isEmpty() || userName.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Please enter correct information", Toast.LENGTH_SHORT).show();
        }
        else {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // closing the progress dialog
                    progressDialog.dismiss();

                    //checking conditions for a successful task or sign up
                    if (task.isSuccessful()) {
                        User user = new User(userName, email, password);
                        // getting the id of a created user which is a unique identity of every user
                        String id = task.getResult().getUser().getUid();

                        //Adding this user to the real time database with his unique id as key
                        userCredentialsDatabase.getReference().child("users").child(id).setValue(user);
                        Toast.makeText(SignUpActivity.this, "Your account is created successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
}