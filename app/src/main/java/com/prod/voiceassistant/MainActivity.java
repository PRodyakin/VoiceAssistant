package com.prod.voiceassistant;

import android.speech.tts.TextToSpeech;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected Button sendButton;
    protected EditText questionField;
    protected TextView chatWindow;

    protected TextToSpeech tts;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionField = findViewById(R.id.questionField);
        chatWindow = findViewById(R.id.chatWindow);

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
        chatWindow.append(">> "+ text + "\n");
        AI.getAnswer(text, new Consumer<String>() {
            @Override
            public void accept(String answer) {
                chatWindow.append("<< "+ answer + "\n");
                tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });


       // chatWindow.append();
        questionField.setText("");

    }
}
