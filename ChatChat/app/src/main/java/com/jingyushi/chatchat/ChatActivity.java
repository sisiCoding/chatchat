package com.jingyushi.chatchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

//    private RecyclerView mRecyclerView;
//    private MessageAdapter mMessageAdapter;

    private ListView mMessageList;
    private MessageAdapter mMessageAdapter;
    private EditText mSentMessage;
    private Button mButton;
    private FirebaseDatabase mFBDatabase;
    private DatabaseReference mDBRef;

    //TODO: get user
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String user = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        // TODO (38) Create layoutManager, a LinearLayoutManager with VERTICAL orientation and shouldReverseLayout == false
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//        // TODO (39) Set the layoutManager on mRecyclerView
//        mRecyclerView.setLayoutManager(layoutManager);
//        // TODO (40) Use setHasFixedSize(true) on mRecyclerView to designate that all items in the list will have the same size
//        mRecyclerView.setHasFixedSize(true);
//        // TODO (41) set mForecastAdapter equal to a new ForecastAdapter
//        mMessageAdapter = new MessageAdapter();
//        // TODO (42) Use mRecyclerView.setAdapter and pass in mForecastAdapter
//        mRecyclerView.setAdapter(mMessageAdapter);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        if(mCurrentUser == null)
        {
            Toast.makeText(this, "Can not get current User", Toast.LENGTH_LONG);
            user = "test";
        }
        user = mCurrentUser.getEmail();

        mFBDatabase = FirebaseDatabase.getInstance();
        mDBRef = mFBDatabase.getReference("messages");

        loadMessages();

        mSentMessage = (EditText) findViewById(R.id.message_tosend);
        mButton = (Button) findViewById(R.id.send_button);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDBRef.push().setValue(new ChatMessage(user, mSentMessage.getText().toString()));
                mSentMessage.setText("");
            }
        });

        mDBRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                mMessageAdapter.add(message);
                //mMesages.setText(message.getName()+" "+message.getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void loadMessages(){
        //Toast.makeText(this, "doing nothing now", Toast.LENGTH_LONG).show();
        mMessageList = (ListView) findViewById(R.id.message_list_view);
        List<ChatMessage> chatMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, chatMessages);
        mMessageList.setAdapter(mMessageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.signout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sign_out){
            mAuth.signOut();
            // show current user to confirm it's signed out
            // return to log in page
            Intent logInIntent = new Intent(this, LogInActivity.class);
            startActivity(logInIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
