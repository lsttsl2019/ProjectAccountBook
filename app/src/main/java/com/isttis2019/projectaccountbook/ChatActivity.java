package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    ListView listView;
    ChatAdapter chatAdapter;

    EditText edMsg;

    ArrayList<MessagItem> messagItems=new ArrayList<>();

    DatabaseReference databaseReference;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       toolbar.setTitle(G.nickName);

        listView=findViewById(R.id.listview);
        chatAdapter=new ChatAdapter(messagItems, getLayoutInflater() );

        listView.setAdapter(chatAdapter);

        edMsg=findViewById(R.id.et);

        databaseReference= FirebaseDatabase.getInstance().getReference("chat");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            MessagItem messagItem =dataSnapshot.getValue(MessagItem.class);
            messagItems.add(messagItem);

            chatAdapter.notifyDataSetChanged();

            listView.setSelection(messagItems.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void clickSend(View view) {
        String nickName=G.nickName;
        String message=edMsg.getText().toString();
        String profilUrl=G.profileUrl;

        Calendar calendar=Calendar.getInstance();
        String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);

        MessagItem messagItem=new MessagItem(nickName, message,time,profilUrl);
        databaseReference.push().setValue(messagItem);
        edMsg.setText("");

            //키보드안보이게하게
        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}













