package com.gmail.lusersks.memorydates.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.gmail.lusersks.memorydates.R;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;
import com.gmail.lusersks.memorydates.model.EventsModel;
import com.gmail.lusersks.memorydates.presenter.EventsPresenter;
import com.gmail.lusersks.memorydates.presenter.Presenter;
import com.gmail.lusersks.memorydates.view.adapter.EventsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.events_calendar)
    CalendarView eventsCalendar;

    @BindView(R.id.load_events_button)
    Button loadEventsButton;

    @BindView(R.id.events_list)
    RecyclerView eventsList;

    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private Presenter presenter;
    private String eventsDay;
    private String eventsMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();

        eventsCalendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            System.out.println("### day: " + dayOfMonth);
            System.out.println("### month: " + month);
            eventsDay = String.valueOf(dayOfMonth);
            eventsMonth = (month < 10 ? "0" : "") + String.valueOf(month + 1);
            loadEvents(view);
        });
    }

    private void initRecyclerView() {
        System.out.println("### initRecyclerView");

        recyclerView = findViewById(R.id.events_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        eventsAdapter = new EventsAdapter(this);
        recyclerView.setAdapter(eventsAdapter);

        System.out.println("### eventsAdapter: " + eventsAdapter);
        System.out.println("### eventsList: " + recyclerView);
    }

    @Override
    public void showList(List<HistoryEvent> eventsList) {
        System.out.println("### eventsList: " + eventsList);

        eventsAdapter.setData(eventsList);
        recyclerView.setAdapter(eventsAdapter);
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
    public List<HistoryEvent> getHistoryEvents() {
        return eventsAdapter.getData();
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void loadEvents(View view) {
        System.out.println("### day: " + eventsDay);
        System.out.println("### month: " + eventsMonth);
        presenter = new EventsPresenter(new EventsModel(eventsMonth, eventsDay), this);
        presenter.loadEvents();
    }
}
