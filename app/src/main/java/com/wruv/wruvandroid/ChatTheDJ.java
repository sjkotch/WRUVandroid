package com.wruv.wruvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatTheDJ extends AppCompatActivity {

    private ArrayList<ChatMessage> messages = new ArrayList<>();
    private OkHttpClient client;

    private final class ChatMessage{

        private String body;
        private String sender;
        private String date;

        public ChatMessage(String body, String sender, String date){
          this.body = body;
          this.sender = sender;
          this.date = date;
        }

        public String getBody(){
            return body;
        }

        public void setBody(String bdy){
            body = bdy;
        }

        public String getSender(){
            return sender;
        }

        public void setSender(String sndr){
            sender = sndr;
        }

        public String getDate(){
            return date;
        }

        public void setDate(String dt){
            date = dt;
        }
    }

    private final class EchoWebSocketListener extends WebSocketListener {

        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
//            webSocket.send("Hello, it's SSaurel !");
//            webSocket.send("What's up ?");
//            webSocket.send(ByteString.decodeHex("deadbeef"));
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            ChatMessage newChat = new ChatMessage(text, "","");
            sendMessage(newChat);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            ChatMessage newChat = new ChatMessage("Error : " + t.getMessage(), "","");
            sendMessage(newChat);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initConnection();
        client = new OkHttpClient();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ImageButton navStream = (ImageButton) findViewById(R.id.navStream);
        ImageButton navLiveFeed = (ImageButton) findViewById(R.id.navLiveFeed);
        ImageButton navSchedule = (ImageButton) findViewById(R.id.navSchedule);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button sendButton = (Button) findViewById(R.id.button);

        navStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Home.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, LiveFeed.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatTheDJ.this, Schedule.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable body = editText.getText();
                ChatMessage newMsg = new ChatMessage(body.toString(),"Me","");
                sendMessage(newMsg);
            }
        });
    }

    public void initConnection(){
        Request request = new Request.Builder().url("http://chat.barbershop.wruv.org/").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(final ChatMessage msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChatMessage newChat = new ChatMessage(msg.getBody(),msg.getSender(),msg.getDate());
                messages.add(newChat);
            }
        });
    }
}