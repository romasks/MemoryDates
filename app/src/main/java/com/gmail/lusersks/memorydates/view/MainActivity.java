package com.gmail.lusersks.memorydates.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
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

    @BindView(R.id.some_text)
    TextView someText;

    @BindView(R.id.events_list)
    RecyclerView eventsList;

    private EventsAdapter adapter;
    private Presenter presenter;
    private String eventsDay;
    private String eventsMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();

        eventsCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                System.out.println("### day: " + dayOfMonth);
                System.out.println("### month: " + month);
                eventsDay = String.valueOf(dayOfMonth);
                eventsMonth = (month < 10 ? "0" : "") + String.valueOf(month + 1);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.events_list);
        adapter = new EventsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showList(List<HistoryEvent> eventsList) {
        StringBuilder msg = new StringBuilder();
        for (HistoryEvent event : eventsList) {
            System.out.println("### History Event: " + event.toString());
            msg.append(event.toString()).append('\n');
        }
        someText.setText(msg.toString());

        adapter.setData(eventsList);
    }

    @Override
    public void showError(String error) {
        someText.setText(error);
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast(getString(R.string.empty_events_list));
    }

    @Override
    public List<HistoryEvent> getHistoryEvents() {
        return adapter.getData();
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
