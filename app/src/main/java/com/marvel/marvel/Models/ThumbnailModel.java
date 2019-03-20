package com.marvel.marvel.Models;

import java.io.Serializable;

public class ThumbnailModel implements Serializable {
    private String path;

    private String extension;

    public String getPath ()
    {
        return path+"."+extension;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public String getExtension ()
    {
        return extension;
    }

    public void setExtension (String extension)
    {
        this.extension = extension;
    }
}
