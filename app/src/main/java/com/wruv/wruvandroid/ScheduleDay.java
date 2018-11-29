package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScheduleDay extends AppCompatActivity {

    private TextView theDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //save and display date, create table cell
        setContentView(R.layout.schedule_day);
        theDate = (TextView) findViewById(R.id.date);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

    }
}
