package com.marvel.marvel.ViewModels.Views;

import com.marvel.marvel.Models.QueriesModel;

import java.util.HashMap;

public interface BaseView<T extends QueriesModel> {
    void noConnection();
    void networkError();
    //HashMap<String,String> getQueries();
    T getQueries();
}
