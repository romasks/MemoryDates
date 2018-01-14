package com.gmail.lusersks.memorydates.presenter;

import com.gmail.lusersks.memorydates.model.Model;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class EventsPresenter implements Presenter {
    private final Model model;
//    private IView view;
    private Subscription subscription = Subscriptions.empty();

    public EventsPresenter(Model model) {
        this.model = model;
    }

    @Override
    public void loadEvents() {

    }

    @Override
    public void onStop() {

    }
}
