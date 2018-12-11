package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ChatTheDJ extends AppCompatActivity {
    private static final String TAG = "ChatTheDJ";
    private boolean pCurrentlyPlaying;

    public final class ChatMessage{

        private String body;
        private String sender;
        private String date;
        private Integer type;

        public ChatMessage(String body, String sender, String date, Integer type){
            this.body = body;
            this.sender = sender;
            this.date = date;
            this.type = type;
        }

        public void printMessage(){
            Log.d(TAG,"Body: " + body + "\n");
            Log.d(TAG,"Sender: " + sender + "\n");
            Log.d(TAG,"Date: " + date + "\n");
            Log.d(TAG,"Type: " + type + "\n");
        }

        public String getBody(){
            return body;
        }

        public void setBody(String bdy){
            body = bdy;
        }

        public Integer getType(){
            return type;
        }

        public void setType(Integer typ){
            type = typ;
        }

        public String getSender(){
            return sender;
        }

        public void setSender(String sndr){
            sender = sndr;
        }

        public String getDate(){
            return date;
        }

        public void setDate(String dt){
            date = dt;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final RecyclerView msgRecyclerView = (RecyclerView)findViewById(R.id.chat_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatRecyclerAdapter chatAppMsgAdapter = new ChatRecyclerAdapter(messages);
        msgRecyclerView.setAdapter(chatAppMsgAdapter);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        Button sendButton = (Button) findViewById(R.id.send_button);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Home.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, LiveFeed.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Schedule.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = editText.getText().toString();
                if(!TextUtils.isEmpty(body)) {
                    ChatMessage newMsg = new ChatMessage(body,"Me",getFormattedDate(),0);
                    editText.setText("");
                    messages.add(newMsg);
                    int newPosition = messages.size() - 1;
                    chatAppMsgAdapter.notifyItemInserted(newPosition);
                    msgRecyclerView.scrollToPosition(newPosition);
                }
            }
        });
    }

    public String getFormattedDate(){
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return df.format(" hh:mm a", new java.util.Date()).toString();
    }
}
