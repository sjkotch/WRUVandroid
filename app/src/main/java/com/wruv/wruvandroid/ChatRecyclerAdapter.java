package com.wruv.wruvandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private List<ChatTheDJ.ChatMessage> msgDtoList = null;

    public ChatRecyclerAdapter(List<ChatTheDJ.ChatMessage> msgDtoList) {
        this.msgDtoList = msgDtoList;
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        ChatTheDJ.ChatMessage msgDto = this.msgDtoList.get(position);
        if(msgDto.getType().equals(1))
        {
            String senderWithDate = "DJ Booth @ " + msgDto.getDate();
            holder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.leftMsgTextView.setText(msgDto.getBody());
            holder.chat_left_msg_sender_view.setText(senderWithDate);
            holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
        }
        else if(msgDto.getType().equals(0))
        {
            String senderWithDate = "Me @ " + msgDto.getDate();
            holder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.rightMsgTextView.setText(msgDto.getBody());
            holder.chat_right_msg_sender_view.setText(senderWithDate);
            holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_message, parent, false);
        return new ChatMessageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(msgDtoList==null)
        {
            msgDtoList = new ArrayList<>();
        }
        return msgDtoList.size();
    }
}