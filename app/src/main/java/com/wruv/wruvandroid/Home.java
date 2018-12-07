package com.wruv.wruvandroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.os.Handler;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;


public class Home extends AppCompatActivity {

    private AudioServiceBinder audioServiceBinder = null;

    private Handler audioProgressUpdateHandler = null;

    // Show played audio progress.
    private ProgressBar backgroundAudioProgress;

    private TextView audioFileUrlTextView;

    // This service connection object is the bridge between activity and background service.
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // Cast and assign background service's onBind method returned iBander object.
            audioServiceBinder = (AudioServiceBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private String audioFile;
    private Handler handler = new Handler();
    private boolean pCurrentlyPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Bind background audio service when activity is created.
        bindAudioService();

        final String audioFileUrl = "http://icecast.uvm.edu:8005/wruv_fm_48";

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        final ImageButton playStop = (ImageButton) findViewById(R.id.playStop);

        if(getIntent().getExtras() != null){
            pCurrentlyPlaying = getIntent().getExtras().getBoolean("pCurrentlyPlaying");
        }

        if(pCurrentlyPlaying){
            playStop.setForeground(getDrawable(R.drawable.stop));
        } else {
            playStop.setForeground(getDrawable(R.drawable.play));
        }

        ((TextView)findViewById(R.id.now_playing_text)).setText("WRUV: Your Better Alternative");

        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LiveFeed.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Schedule.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ChatTheDJ.class);
                i.putExtra("pCurrentlyPlaying",pCurrentlyPlaying);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        playStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!audioServiceBinder.isPlaying()) {
                    Toast.makeText(getApplicationContext(), "Buffering...", Toast.LENGTH_LONG).show();

                    // Set web audio file url
                    audioServiceBinder.setAudioFileUrl(audioFileUrl);

                    // Web audio is a stream audio.
                    audioServiceBinder.setStreamAudio(true);

                    // Set application context.
                    audioServiceBinder.setContext(getApplicationContext());

                    // Start audio in background service.
                    audioServiceBinder.startAudio();

                    playStop.setForeground(getDrawable(R.drawable.stop));
                    pCurrentlyPlaying = !pCurrentlyPlaying;
                } else {
                    audioServiceBinder.stopAudio();
                    Toast.makeText(getApplicationContext(), "Stopping Stream.", Toast.LENGTH_LONG).show();
                    playStop.setForeground(getDrawable(R.drawable.play));
                    pCurrentlyPlaying = !pCurrentlyPlaying;
                }

            }
        });

    }

    // Bind background service with caller activity. Then this activity can use
    // background service's AudioServiceBinder instance to invoke related methods.
    private void bindAudioService()
    {
        if(audioServiceBinder == null) {
            Intent intent = new Intent(Home.this, AudioService.class);

            // Below code will invoke serviceConnection's onServiceConnected method.
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    // Unbound background audio service with caller activity.
    private void unBoundAudioService()
    {
        if(audioServiceBinder != null) {
            unbindService(serviceConnection);
        }
    }

    @Override
    protected void onDestroy() {
        // Unbound background audio service when activity is destroyed.
        unBoundAudioService();
        super.onDestroy();
    }

}