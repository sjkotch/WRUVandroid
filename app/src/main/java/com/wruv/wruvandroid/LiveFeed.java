package com.wruv.wruvandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LiveFeed extends AppCompatActivity {
    private static final String TAG = "LiveFeed";
    private String audioFile;
    private Handler handler = new Handler();
    private boolean pCurrentlyPlaying = false;

    private RecyclerView mRecyclerView;
    private LiveFeedRecyclerAdaptor adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ParseObject> scheduleArray = new ArrayList<>();


    public static final String AUDIO_FILE_NAME = "C:/Users/leann/AndroidStudioProjects/WRUVandroid/MusicFolder/Marias_IDontKnowYou.mp3";
    String[] tokens = AUDIO_FILE_NAME.split(".+?/(?=[^/]+$)");
    String songName = tokens[1];

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
        mRecyclerView = (RecyclerView) findViewById(R.id.liveFeedRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseQuery<ParseObject> query = ParseQuery.getQuery("LiveFeed");
        query.orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException error) {
                if (error == null)  {
                    scheduleArray = objects;
                    for(ParseObject o : objects) {
                        Log.d(TAG, "LiveFeed : " + o.getString("song"));
                        Log.d(TAG, "LiveFeed : " + o.getObjectId());
                    }
                    // error
                } else {
                }
                RecyclerView recyclerView = findViewById(R.id.liveFeedRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(LiveFeed.this));
                adapter = new LiveFeedRecyclerAdaptor(LiveFeed.this, scheduleArray);
                recyclerView.setAdapter(adapter);
                Log.d(TAG,"Finish recyclerView");
            }
        });

        
        //Setting buttons to corresponding ID's
        buttFilter = (ImageButton) findViewById(R.id.buttFilter);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);
        final ImageButton playStop = (ImageButton) findViewById(R.id.playStop);
        playStop.setImageResource(R.drawable.play);


        this.getIntent().putExtra(AUDIO_FILE_NAME,AUDIO_FILE_NAME);
        audioFile = this.getIntent().getStringExtra(songName);
        ((TextView)findViewById(R.id.now_playing_text)).setText(songName);

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

        playStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int image = pCurrentlyPlaying ? R.drawable.stop : R.drawable.play;
                playStop.setImageResource(image);
                pCurrentlyPlaying = !pCurrentlyPlaying ;

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
