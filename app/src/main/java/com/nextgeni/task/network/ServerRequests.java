package com.nextgeni.task.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nextgeni.task.apis.RestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ServerRequests {


    private static RestAPI restAPI;

    public static RestAPI getInstance(final Context context) {
        if (restAPI == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            String baseUrl = "https://localforce-labs.com/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            restAPI = retrofit.create(RestAPI.class);
            return restAPI;
        } else {
            return restAPI;
        }
    }

}
