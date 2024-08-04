package com.example.cherryontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail;
    EditText password;

    private FirebaseAuth auth;

    // for loading bubble symbol
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        //for progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging Account");
        progressDialog.setMessage("Logging In.");
    }

    public void logIn(View view){
        //starting the progress dialog
        progressDialog.show();

        userEmail = findViewById(R.id.userEmail);
        password = findViewById(R.id.password);

        String userEmailVal = userEmail.getText().toString();
        String passwordVal = password.getText().toString();

        if (userEmailVal.isEmpty() || passwordVal.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please enter correct information", Toast.LENGTH_SHORT).show();
        }
        else {

            auth.signInWithEmailAndPassword(userEmailVal, passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    //               closing progress dialog
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }      }

}