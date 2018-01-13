package com.gmail.lusersks.memorydates.model;

import android.database.Observable;

import java.util.List;

public interface Model {
    Observable<List<HistoryEvent>> getMemoryDates();
}
