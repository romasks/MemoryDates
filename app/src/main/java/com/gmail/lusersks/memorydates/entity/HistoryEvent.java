package com.gmail.lusersks.memorydates.entity;

import com.google.gson.annotations.SerializedName;

public class HistoryEvent {

    private Integer id;
    private String type;
    private String name;
    private String description;
    private String date;

    @SerializedName("html_url")
    private String url;

    @Override
    public String toString() {
        return "{ " + id + ". type: " + type + ", name: " + name + ", description: " + description + ", date: " + date + " }";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
