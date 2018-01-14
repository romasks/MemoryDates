package com.gmail.lusersks.memorydates.presenter;

import com.gmail.lusersks.memorydates.model.Model;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class PresenterImpl implements Presenter {
    private final Model model;
//    private View view;
    private Subscription subscription = Subscriptions.empty();

    public PresenterImpl(Model model) {
        this.model = model;
    }

    @Override
    public void loadEvents() {

    }

    @Override
    public void onStop() {

    }
}
