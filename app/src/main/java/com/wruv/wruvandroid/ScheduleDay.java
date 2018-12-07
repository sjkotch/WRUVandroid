package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.lang.reflect.Array;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleDay extends AppCompatActivity{
    private static final String TAG = "ScheduleDay";

    private boolean pCurrentlyPlaying;

    private TextView theDate;
    private RecyclerView mRecyclerView;
    private ScheduleRecyclerAdaptor adapter;
    private ArrayList<String> mData = new ArrayList<>();

    private ArrayList<ArrayList<String>> scheduleData = new ArrayList<ArrayList<String>>();

    private RecyclerView.LayoutManager mLayoutManager;

    private List<ParseObject>scheduleArray = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }
        //save and display date, create table cell
        setContentView(R.layout.schedule_day);
        theDate = (TextView) findViewById(R.id.date);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        String textDate = incomingIntent.getStringExtra("textDate");

        scheduleArray = incomingIntent.getParcelableArrayListExtra("scheduleArray");

        theDate.setText(textDate);


        mRecyclerView = (RecyclerView) findViewById(R.id.schedule_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.schedule_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScheduleRecyclerAdaptor(this, scheduleArray);
        recyclerView.setAdapter(adapter);
        Log.d(TAG,"Finish recyclerView");

    }


}
