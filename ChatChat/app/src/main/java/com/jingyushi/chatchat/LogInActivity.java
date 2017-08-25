package com.jingyushi.chatchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = LogInActivity.class.getSimpleName();

    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*********OnCreate*********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        // create a connection to firebase db, verify if account exists
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "*********OnStart*********");
        // TODO : getCurrentUser()
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser)
    {
        if(currentUser != null) {
            Toast.makeText(this, "Logged in!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, FriendsActivity.class));
        }
        else{
            // do nothing for now
        }
    }

    // open sign up activity on sign up button clicked
    // TODO: use separate page just to pick sign up / login
    public void onSignUpClicked(View view){
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    //TODO: getting error on api 26
    public void onLogInCliked(View view){
        mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Successfully log in");
                    currentUser = mAuth.getCurrentUser();
                    updateUI(currentUser);
                }
                else{
                    Log.d(TAG, "Failed to log in");
                    Toast.makeText(LogInActivity.this, "Failed to log in", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "*********OnPause*********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "*********OnResume*********");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "*********OnStop*********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "*********OnDestroy*********");
    }
}
