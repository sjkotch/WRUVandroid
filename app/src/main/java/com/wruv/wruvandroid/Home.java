package com.wruv.wruvandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

//        navStream.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home.this, Home.class);
//                startActivity(i);
//            }
//        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LiveFeed.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Schedule.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ChatTheDJ.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
    }
}
