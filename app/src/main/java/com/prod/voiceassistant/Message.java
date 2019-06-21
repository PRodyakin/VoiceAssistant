package com.prod.voiceassistant;

import java.util.Date;

public class Message {
    public String text;
    public Date date;
    public String user;
    public boolean is_sent;


    public Message(String text, boolean is_sent,String user) {
        this.text = text;
        this.user = user;
        this.date = new Date();
        this.is_sent = is_sent;
    }
}
