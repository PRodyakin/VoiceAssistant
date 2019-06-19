package com.prod.voiceassistant;

import android.support.v4.util.Consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
    public static void getAnswer(String user_question, final Consumer<String> callBack){

        HashMap<String, String> dataBase = new HashMap<String, String>(){{
            put("привет","Здравствуй");
            put("как дела?","Дела норм");
            put("что делаешь?","Отвечаю на вопросы");
            put("привет","Здравствуй");
            put("привет","Здравствуй");
        }};

        user_question = user_question.toLowerCase();

        final List<String> answers = new ArrayList<>();

        for (String dataBase_question : dataBase.keySet()){
            if (user_question.contains(dataBase_question)){
                answers.add(dataBase.get(dataBase_question));
            }

        }

        Pattern cityPattern = Pattern.compile("какая погода в городе (\\p{L}+)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = cityPattern.matcher(user_question);
        if (matcher.find()){
            String cityName = matcher.group(1);
            Weather.getWeather(cityName, new Consumer<String>() {
                @Override
                public void accept(String weather_forecast) {
                    answers.add(weather_forecast);
                    callBack.accept(String.join(", ", answers ));
                }
            });
            answers.add("не нашел "+cityName);
        }


        if (answers.isEmpty()){
            answers.add("OK");
        }
        callBack.accept(String.join(", ", answers ));
        //return String.join(", ", answers );

    }
}
