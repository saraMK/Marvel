package com.marvel.marvel.ViewModels.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.Models.ComicStoriesModel;
import com.marvel.marvel.Network.CallBackApis;
import com.marvel.marvel.Network.NetworkClient;
import com.marvel.marvel.Network.NetworkInterfaceApis;
import com.marvel.marvel.ViewModels.Views.BaseView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarvelHeroesRepository  {

    NetworkInterfaceApis apis;
    BaseView  baseView;
    public MarvelHeroesRepository(BaseView baseView, Context context){
        apis=NetworkClient.getApiService(context);
        this.baseView=baseView;
    }

    public LiveData<CharactersModel> getCharacters(){
        final MutableLiveData<CharactersModel> liveData=new MutableLiveData<>();
        Call<CharactersModel> call=apis.getCharacters(baseView.getQueries().getMap());
        call.enqueue(new CallBackApis<CharactersModel>(baseView){
            @Override
            public void onResponse(CharactersModel body) {
                liveData.postValue(body);
            }
        });


        return liveData;
    }

    public LiveData<CharactersModel> getComicEtc() {
        ComicStoriesModel comicStoriesModel= (ComicStoriesModel) baseView.getQueries();
        final MutableLiveData<CharactersModel> liveData=new MutableLiveData<>();
        Call<CharactersModel> call=apis.getComicsStories(comicStoriesModel.getId(),comicStoriesModel.getTag(),baseView.getQueries().getMap());
        call.enqueue(new CallBackApis<CharactersModel>(baseView){
            @Override
            public void onResponse(CharactersModel body) {
                liveData.postValue(body);
            }
        });


        return liveData;
    }
}
