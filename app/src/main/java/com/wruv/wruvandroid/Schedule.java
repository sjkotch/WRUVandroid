package com.wruv.wruvandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

//        GET http://wruv.creek.fm/api/schedule;

public class Schedule extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(R.id.calendarLayout);


    }

}
