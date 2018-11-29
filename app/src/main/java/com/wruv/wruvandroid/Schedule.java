package com.wruv.wruvandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

//        GET http://wruv.creek.fm/api/schedule;

public class Schedule extends AppCompatActivity {
    private static final String TAG = "Schedule";

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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG,"onSelectedDayChange" + dayOfMonth + "/" + month + "/"+year);
                String date = dayOfMonth + "/" + month + "/"+year;
                Intent intent = new Intent(Schedule.this, ScheduleDay.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
        query.getInBackground("sj69I01ndC", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    String playerName = object.getString("dj");
                    Log.d(TAG,"onQuery " + playerName);

                } else {
                    // something went wrong
                }
            }
        });

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
