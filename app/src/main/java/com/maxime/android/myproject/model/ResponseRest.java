package com.maxime.android.myproject.model;

import com.maxime.android.myproject.model.Pokemon;

import java.util.List;

public class ResponseRest {

    private Integer count;
    private String next;
    private List<Pokemon> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}