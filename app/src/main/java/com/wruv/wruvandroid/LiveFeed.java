package com.wruv.wruvandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class LiveFeed extends AppCompatActivity {
    private String audioFile;
    private Handler handler = new Handler();
    private boolean pCurrentlyPlaying;

    //XML elements
    private ImageButton buttFilter;

    //Variables
    Boolean songsChecked = true;
    Boolean djChecked = true;
    Boolean otherChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, Home.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, Schedule.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, ChatTheDJ.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }


}
