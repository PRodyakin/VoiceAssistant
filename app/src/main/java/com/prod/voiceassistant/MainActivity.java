package com.prod.voiceassistant;

import android.speech.tts.TextToSpeech;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected Button sendButton;
    protected EditText questionField;
    protected RecyclerView chatMessageList;

    protected TextToSpeech tts;

    protected MessageListAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionField = findViewById(R.id.questionField);
        chatMessageList = findViewById(R.id.chatMessageList);

        adapter = new MessageListAdapter();
        chatMessageList.setLayoutManager(new LinearLayoutManager(this));
        chatMessageList.setAdapter(adapter);
                
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //savedInstanceState.clear();
              onClickSendButton();

            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(new Locale("ru"));
                }
            }
        });

    }

    protected void onClickSendButton(){

        String text = questionField.getText().toString();
        adapter.messageList.add(new Message(text, true, "UserName"));
        adapter.notifyDataSetChanged();
        AI.getAnswer(text, new Consumer<String>() {
            @Override
            public void accept(String answer) {
                adapter.messageList.add(new Message(answer, false, "Assistant"));
                tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null,null);
                adapter.notifyDataSetChanged();
                chatMessageList.scrollToPosition(adapter.messageList.size()-1);
            }
        });


       // chatWindow.append();
        questionField.setText("");

    }
}
