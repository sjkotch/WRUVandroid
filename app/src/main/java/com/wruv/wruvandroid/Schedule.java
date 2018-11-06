package com.wruv.wruvandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

//        GET http://wruv.creek.fm/api/schedule;

public class Schedule extends AppCompatActivity {

    CalendarView calendarView;

    private Button navStream;
    private Button navLiveFeed;
    private Button navSchedule;
    private Button navChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarLayout);

        navStream = (Button) findViewById(R.id.navStream);
        navLiveFeed = (Button) findViewById(R.id.navLiveFeed);
        navSchedule = (Button) findViewById(R.id.navSchedule);
        navChat = (Button) findViewById(R.id.navChat);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, Home.class);
                startActivity(i);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, LiveFeed.class);
                startActivity(i);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, Schedule.class);
                startActivity(i);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, ChatTheDJ.class);
                startActivity(i);
            }
        });
    }

}
