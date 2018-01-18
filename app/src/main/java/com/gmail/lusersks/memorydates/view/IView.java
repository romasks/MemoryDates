package com.gmail.lusersks.memorydates.view;

import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.List;

public interface IView {
    void showList(List<HistoryEvent> eventsList);
    void showError(String Error);
    void showEmptyList();
    List<HistoryEvent> getHistoryEvents();
}
