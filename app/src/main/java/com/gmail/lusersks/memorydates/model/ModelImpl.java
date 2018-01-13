package com.gmail.lusersks.memorydates.model;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {
    private String month;
    private String day;
    private GithubService api;

    ModelImpl(String month, String day, GithubService api) {
        this.month = month;
        this.day = day;
        this.api = api;
    }

    @Override
    public Observable<List<HistoryEvent>> retrieveInfo() {
        return api.getEvents(month, day).subscribeOn(Schedulers.io());
    }
}
