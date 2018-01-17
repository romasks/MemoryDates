package com.gmail.lusersks.memorydates.model;

import com.gmail.lusersks.memorydates.entity.GithubService;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class EventsModel implements Model {
    private String month;
    private String day;
    private GithubService api;

    public EventsModel(String month, String day) {
        this.month = month;
        this.day = day;
        this.api = buildApiService();
    }

    private GithubService buildApiService() {
        return new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
    }

    @Override
    public Observable<List<HistoryEvent>> retrieveInfo() {
        return api.getEvents(month, day).subscribeOn(Schedulers.io());
    }
}
