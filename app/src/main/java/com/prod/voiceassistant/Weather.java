package com.prod.voiceassistant;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Weather {
    public static class Condition {
        @SerializedName("text")
        public String text;
    }

    public static class Forecast {
        @SerializedName("temp_c")
        public float temperature;

        @SerializedName("condition")
        public Condition condition;
    }

    public static class ApiResult {
        @SerializedName("current")
        public Forecast current;
    }

    public interface WeatherService{
        @GET("/v1/current.json?key=ef1582c82180438dbdb130630191906")
        Call<ApiResult> getCurrentWeather(@Query("q") String city, @Query("lang") String lang);
    }

    public static void getWeather(String city, final Consumer<String> callback){
        Retrofit retr = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<ApiResult> call = retr.create(WeatherService.class)
                .getCurrentWeather(city,"ru");

        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ApiResult result = response.body();
                String answer = "Там сейчас "+result.current.condition.text + " , примерно "+(int) result.current.temperature + " градусов";
                callback.accept(answer);
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.w("WEATHER",t.getMessage());
            }
        });


    }
}




