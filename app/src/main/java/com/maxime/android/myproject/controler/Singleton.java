package com.maxime.android.myproject.controler;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxime.android.myproject.data.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Singleton {
    public static final String BASE_URL2 = "https://raw.githubusercontent.com/";
    private static Gson gsonInstance;
    private static Api apiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if (gsonInstance == null) {
            return new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static Api getApi(){
        if(apiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            apiInstance = retrofit.create(Api.class);
        }
        return apiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context) {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("Application", Context.MODE_PRIVATE);
        }

        return sharedPreferencesInstance;
    }
}
