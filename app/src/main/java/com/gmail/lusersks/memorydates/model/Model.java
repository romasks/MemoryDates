package com.gmail.lusersks.memorydates.model;

import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.List;

import rx.Observable;

public interface Model {
    Observable<List<HistoryEvent>> retrieveInfo();
}
