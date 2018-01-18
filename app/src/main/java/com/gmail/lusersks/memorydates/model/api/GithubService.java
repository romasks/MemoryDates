package com.gmail.lusersks.memorydates.model.api;

import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {

    // https://raw.githubusercontent.com/romasks/memory-dates-data/master/07/27.json

    @GET("romasks/memory-dates-data/master/{month}/{day}/events.json")
    Observable<List<HistoryEvent>> getEvents(@Path("month") String month, @Path("day") String day);
}
