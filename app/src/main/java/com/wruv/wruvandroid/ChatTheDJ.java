package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChatTheDJ extends AppCompatActivity {
    private boolean pCurrentlyPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);

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
    }

}
