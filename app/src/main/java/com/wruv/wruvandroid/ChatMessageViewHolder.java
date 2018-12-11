package com.wruv.wruvandroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    LinearLayout leftMsgLayout;
    LinearLayout rightMsgLayout;
    TextView leftMsgTextView;
    TextView rightMsgTextView;
    TextView chat_right_msg_sender_view;
    TextView chat_left_msg_sender_view;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        if(itemView != null) {
            leftMsgLayout = itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout = itemView.findViewById(R.id.chat_right_msg_layout);
            leftMsgTextView = itemView.findViewById(R.id.chat_left_msg_text_view);
            rightMsgTextView = itemView.findViewById(R.id.chat_right_msg_text_view);
            chat_right_msg_sender_view = itemView.findViewById(R.id.chat_right_msg_sender_view);
            chat_left_msg_sender_view = itemView.findViewById(R.id.chat_left_msg_sender_view);
        }
    }
}
