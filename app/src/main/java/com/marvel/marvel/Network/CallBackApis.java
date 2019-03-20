package com.marvel.marvel.Network;

import android.util.Log;

import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.ViewModels.Views.BaseView;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CallBackApis<T> implements Callback<T> {

    private BaseView baseView;

    public CallBackApis(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d("onResponse", response.code() + "\t " + call.request());
        if (response.code() == 200) {
            onResponse(response.body());
        } else if (response.code() == 504)
            baseView.noConnection();
        else baseView.networkError();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("onResponse", t.toString() + "\n " + call.request());
        if (t instanceof ConnectException || t instanceof UnknownHostException)
            baseView.noConnection();
        else baseView.networkError();
    }

    public abstract void onResponse(T body);

}
