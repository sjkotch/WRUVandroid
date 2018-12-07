package com.wruv.wruvandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

//        GET http://wruv.creek.fm/api/schedule;

public class Schedule extends AppCompatActivity {
    private static final String TAG = "Schedule";

    private String audioFile;
    private Handler handler = new Handler();
    private boolean pCurrentlyPlaying;

    CalendarView calendarView;

    private List<ParseObject> scheduleArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }

        calendarView = (CalendarView) findViewById(R.id.calendarLayout);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG,"onSelectedDayChange" + dayOfMonth + "/" + month + "/"+year);
                String date = month + "/" + dayOfMonth + "/" +year;
                String textDate = getTextDate(month, dayOfMonth, year);
                Intent intent = new Intent(Schedule.this, ScheduleDay.class);
                intent.putExtra("date", date);
                intent.putExtra("textDate", textDate);
                intent.putParcelableArrayListExtra("scheduleArray", (ArrayList<? extends Parcelable>) scheduleArray);
                intent.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
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

        //query schedule class from back4app
        querySchedule();


        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, Home.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, LiveFeed.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Schedule.this, ChatTheDJ.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }

    void querySchedule() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException error) {
                if (error == null)  {
                    scheduleArray = objects;
                    for(ParseObject o : objects) {
                        Log.d(TAG, "DJ : " + o.getString("dj"));
                        Log.d(TAG, "DJ : " + o.getObjectId());

                    }
                    // error
                    } else {
                }
            }
        });
    }
    public String getTextDate(int month, int dayOfMonth, int year){
        String newMonth = Integer.toString(month);
        String returnedString ="";
        switch(newMonth){
            case "01": returnedString = returnedString + "January";
                break;
            case "02": returnedString = returnedString + "February";
                break;
            case "03": returnedString = returnedString + "March";
                break;
            case "04": returnedString = returnedString + "April";
                break;
            case "05": returnedString = returnedString + "May";
                break;
            case "06": returnedString = returnedString + "June";
                break;
            case "07": returnedString = returnedString +"July";
                break;
            case "08": returnedString = returnedString +"August";
                break;
            case "09": returnedString = returnedString +"September";
                break;
            case "10": returnedString = returnedString +"October";
                break;
            case "11": returnedString = returnedString +"November";
                break;
            case "12": returnedString = returnedString +"December";
                break;

        }
        returnedString = returnedString + " " + dayOfMonth + ", " + year;
        return returnedString;
    }
}
