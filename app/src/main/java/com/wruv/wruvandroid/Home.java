package com.wruv.wruvandroid;

import android.content.Intent;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.os.Handler;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class Home extends AppCompatActivity {

    private String audioFile;
    private Handler handler = new Handler();
    private boolean pCurrentlyPlaying = false;


    public static final String AUDIO_FILE_NAME = "C:/Users/leann/AndroidStudioProjects/WRUVandroid/MusicFolder/Marias_IDontKnowYou.mp3";
    String[] tokens = AUDIO_FILE_NAME.split(".+?/(?=[^/]+$)");
    String songName = tokens[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        ImageButton navChat = (ImageButton) findViewById(R.id.navChat);

        final String url = "http://icecast.uvm.edu:8005/wruv_fm_128"; // your URL here
        final MediaPlayer mediaPlayer = new MediaPlayer();

        final ImageButton playStop = (ImageButton) findViewById(R.id.playStop);
        playStop.setImageResource(R.drawable.play);

        this.getIntent().putExtra(AUDIO_FILE_NAME,AUDIO_FILE_NAME);
        audioFile = this.getIntent().getStringExtra(songName);
        ((TextView)findViewById(R.id.now_playing_text)).setText(songName);

//        navStream.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home.this, Home.class);
//                startActivity(i);
//            }
//        });

        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LiveFeed.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Schedule.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ChatTheDJ.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        playStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mediaPlayer.isPlaying()){
                    Toast.makeText(getApplicationContext(), "Starting stream",Toast.LENGTH_SHORT).show();
                    try {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setLooping(true);
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                } else {
                    Toast.makeText(getApplicationContext(), "Stopping stream",Toast.LENGTH_SHORT).show();
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                int image = pCurrentlyPlaying ? R.drawable.play : R.drawable.stop;
                playStop.setForeground(getDrawable(image));
                pCurrentlyPlaying = !pCurrentlyPlaying;
            }
        });


    }


}