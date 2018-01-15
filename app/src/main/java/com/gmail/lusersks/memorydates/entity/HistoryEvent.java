package com.gmail.lusersks.memorydates.entity;

public class HistoryEvent {

    private String type;
    private String name;
    private String description;
    private String date;

    @Override
    public String toString() {
        return "{ type: " + type + ", name: " + name + ", description: " + description + ", date: " + date + " }";
    }
}
