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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = LogInActivity.class.getSimpleName();

    EditText mName;
    EditText mEmail;
    EditText mPassword;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    FirebaseDatabase mDb;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*********OnCreate*********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //TODO name is not used in authentication, need to create user ref in database and store all data
        mName = (EditText) findViewById(R.id.name_sign_up);
        mEmail = (EditText) findViewById(R.id.email_sign_up);
        mPassword = (EditText) findViewById(R.id.password_sign_up);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onSubmitClicked(View view){
        mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    mUser = mAuth.getCurrentUser();
                    updateUserProfile();
                    updateUserDb();
                    Toast.makeText(SignUpActivity.this, "Signed In", Toast.LENGTH_LONG).show();
                    //TODO start chat activity, better way
                    Intent chatIntent = new Intent(SignUpActivity.this, FriendsActivity.class);
                    startActivity(chatIntent);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //TODO clear and start again
                }
            }
        });
    }

    private void updateUserProfile(){
        // do nothing now
    }

    private void updateUserDb(){
        mDb = FirebaseDatabase.getInstance();
        mRef = mDb.getReference("user");
        mRef.push().setValue(new User(mName.getText().toString(), mEmail.getText().toString(), new ArrayList<User>()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "*********OnStart*********");
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
