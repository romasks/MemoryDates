package com.gmail.lusersks.memorydates.presenter;

import com.gmail.lusersks.memorydates.model.Model;
import com.gmail.lusersks.memorydates.view.IView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

public class EventsPresenter implements Presenter {
    private final Model model;
    private IView view;
    private Subscription subscription = Subscriptions.empty();

    public EventsPresenter(Model model, IView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadEvents() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(data -> {
            if (data != null && !data.isEmpty()) {
                view.showList(data);
            } else {
                view.showEmptyList();
            }
        }, throwable -> {
            view.showError(throwable.getMessage());
        });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
