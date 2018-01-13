package com.gmail.lusersks.memorydates.model;

import java.util.List;

import rx.Observable;

public interface Model {
    Observable<List<HistoryEvent>> retrieveInfo();
}
