package com.marvel.marvel.Network;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.marvel.marvel.Utiles.GeneralStrings.BASE_URL;

/**
 * Created by Sara on 10/14/2018.
 */

public class NetworkClient {
    public static Retrofit retrofit;
    private static NetworkInterfaceApis apis;
    private HttpService httpService;
    public static NetworkInterfaceApis getApiService(Context context) {

        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpService httpService =new HttpService(context);
            OkHttpClient okHttpClient = builder.build();
             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpService.getHttpClient())
                    .build();

            apis = retrofit.create(NetworkInterfaceApis.class);
        }

        return apis;
    }

 }

