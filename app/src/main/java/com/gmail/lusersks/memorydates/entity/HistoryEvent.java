package com.gmail.lusersks.memorydates.entity;

public class HistoryEvent {

    private Integer id;
    private String type;
    private String name;
    private String description;
    private String date;

    @Override
    public String toString() {
        return "{ " + id + ". type: " + type + ", name: " + name + ", description: " + description + ", date: " + date + " }";
    }
}
