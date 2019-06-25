package com.submission.studio.moohat.bukatoko.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {


    private static Retrofit retrofit;
    public static Retrofit getUrl(){
         retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
