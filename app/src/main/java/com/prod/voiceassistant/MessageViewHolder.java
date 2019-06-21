package com.prod.voiceassistant;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MessageViewHolder extends RecyclerView.ViewHolder  {
    protected TextView messageTextView;
    protected TextView messageDateview;
    protected TextView userTextView;

    public MessageViewHolder(View item){
        super(item);
        messageTextView = item.findViewById(R.id.messageTextView);
        messageDateview = item.findViewById(R.id.messageDateview);
        userTextView = item.findViewById(R.id.userTextView);
    }

    public void bind(Message message){

        messageTextView.setText(message.text);
        DateFormat fmt = new SimpleDateFormat("HH:mm");
        messageDateview.setText(fmt.format(message.date));
        userTextView.setText(message.user);


    }
}
