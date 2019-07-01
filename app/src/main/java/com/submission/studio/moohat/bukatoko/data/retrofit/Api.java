package com.submission.studio.moohat.bukatoko.data.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {


    private static Retrofit retrofit;
    public static Retrofit getUrl(){
         retrofit = new Retrofit.Builder()
//                .baseUrl("http://kursus-online.lazday.com/api/")
                .baseUrl("http://172.17.100.2:8000/api/") //10.0.2.3 untuk genymotion 10.0.2.2 untuk nox cek pake fing app di nox emulatornya disini dapet route : 172.17.100.2
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
