package com.marvel.marvel.Models;

public class ComicStoriesModel extends QueriesModel {
    private String Tag ,id;
    public ComicStoriesModel(String tag,String id){
        super();
      Tag=tag;
      this.id=id;
    }

    public String getTag() {
        return Tag;
    }

    public String getId() {
        return id;
    }
}
