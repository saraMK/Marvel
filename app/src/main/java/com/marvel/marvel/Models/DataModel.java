package com.marvel.marvel.Models;

import java.util.List;

public class DataModel {
    private String total;

    private String offset;

    private String limit;

    private int count;

    private List<ResultsModel> results;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
    }

    public String getLimit ()
    {
        return limit;
    }

    public void setLimit (String limit)
    {
        this.limit = limit;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public List<ResultsModel> getResults ()
    {
        return results;
    }

    public void setResults (List<ResultsModel> results)
    {
        this.results = results;
    }
}
