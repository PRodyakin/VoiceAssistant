package com.prod.voiceassistant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    public List<Message> messageList = new ArrayList<>();

    private static final int ASSISTANT_TYPE = 0;
    private static final int USER_TYPE = 1;

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = messageList.get(i);
        ((MessageViewHolder) viewHolder).bind(message);

    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        if (message.is_sent){
            return USER_TYPE;
        }else{
            return ASSISTANT_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if(i == USER_TYPE){


            view = layoutInflater.inflate(R.layout.user_message, viewGroup, false);

        }else{

            view = layoutInflater.inflate(R.layout.assistant_message, viewGroup, false);
        }
        return new MessageViewHolder(view);
    }
}
