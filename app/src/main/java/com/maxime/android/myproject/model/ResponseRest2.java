package com.maxime.android.myproject.model;

import java.util.List;

public class ResponseRest2 {
    private Integer count;
    private String next;
    private List<Ability> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Ability> getResults() {
        return results;
    }
}
