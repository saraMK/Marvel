package com.marvel.marvel.Network;

import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.Models.DataModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface NetworkInterfaceApis {
    @GET("v1/public/characters")
    Call<CharactersModel> getCharacters(@QueryMap HashMap<String, String> queries);

    @GET("v1/public/characters/{id}/{tag}")
    Call<CharactersModel> getComicsStories(@Path("id") String id,
                                           @Path("tag") String tag,
                                           @QueryMap HashMap<String, String> queries);
}
