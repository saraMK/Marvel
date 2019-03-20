package com.marvel.marvel.Models;

import java.io.Serializable;
import java.io.SerializablePermission;

public class ResultsModel implements Serializable {


    private ThumbnailModel thumbnail;

    private String name;

    private String description;

    private String modified;

    private String id;
    private String title;

    public ThumbnailModel getThumbnail ()
    {
        return thumbnail ;
    }

    public void setThumbnail (ThumbnailModel thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getName ()
    {
        return name!=null?name:title;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getModified ()
    {
        return modified;
    }

    public void setModified (String modified)
    {
        this.modified = modified;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

}
