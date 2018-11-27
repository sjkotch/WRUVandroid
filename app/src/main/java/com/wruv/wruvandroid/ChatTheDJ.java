package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ChatTheDJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Home.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, LiveFeed.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Schedule.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
//        navChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Schedule.this, ChatTheDJ.class);
//                startActivity(i);
//            }
//        });
    }
}
