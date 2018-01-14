package com.gmail.lusersks.memorydates.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CalendarView;

import com.gmail.lusersks.memorydates.R;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.events_calendar)
    CalendarView eventsCalendar;

    @BindView(R.id.events_list)
    RecyclerView eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showList(List<HistoryEvent> eventsList) {

    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast(getString(R.string.empty_events_list));
    }

    @Override
    public String getHistoryEvents() {
        return null;
    }

    private void makeToast(String error) {
        // TODO generate toast
    }
}
