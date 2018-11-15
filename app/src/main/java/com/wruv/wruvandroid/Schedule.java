package com.wruv.wruvandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

//        GET http://wruv.creek.fm/api/schedule;

public class Schedule extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarLayout);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, Home.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, LiveFeed.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
//        navSchedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Schedule.this, Schedule.class);
//                startActivity(i);
//            }
//        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, ChatTheDJ.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
    }

}
