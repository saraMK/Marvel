package com.marvel.marvel.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.ViewModels.Repositories.MarvelHeroesRepository;
import com.marvel.marvel.ViewModels.Views.BaseView;

public class MarvelHeroesCharacters extends AndroidViewModel {
    private MarvelHeroesRepository marvelHeroesRepository;
    Application application;
    public MarvelHeroesCharacters(@NonNull Application application) {
        super(application);
        this.application=application;

    }

    public void setRepo(BaseView baseView){
        marvelHeroesRepository=new MarvelHeroesRepository(baseView,application.getBaseContext());
    }
    public LiveData<CharactersModel> getCharacters(){
        return marvelHeroesRepository.getCharacters();
    }

    public LiveData<CharactersModel> getComicEtc(){
        return marvelHeroesRepository.getComicEtc();
    }
}
