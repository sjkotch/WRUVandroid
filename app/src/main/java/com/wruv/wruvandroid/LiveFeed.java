package com.wruv.wruvandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class LiveFeed extends AppCompatActivity {

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

        //Setting buttons to corresponding ID's
        buttFilter = (ImageButton) findViewById(R.id.buttFilter);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, Home.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
//        navLiveFeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LiveFeed.this, LiveFeed.class);
//                startActivity(i);
//            }
//        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, Schedule.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveFeed.this, ChatTheDJ.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });



        //OnClickListener for Filter Button
        buttFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(LiveFeed.this, buttFilter);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu_filter_live_feed, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        item.setChecked(!item.isChecked());
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

    }


}
