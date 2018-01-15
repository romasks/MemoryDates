package com.gmail.lusersks.memorydates.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.Calendar;
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

    private Presenter presenter;
    private String eventsDay;
    private String eventsMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        eventsCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                System.out.println("### day: " + dayOfMonth);
                System.out.println("### month: " + month);
                eventsDay = String.valueOf(dayOfMonth);
                eventsMonth = (month < 10 ? "0" : "") + String.valueOf(month + 1);
            }
        });
    }

    @Override
    public void showList(List<HistoryEvent> eventsList) {
        String msg = "";
        for (HistoryEvent event : eventsList) {
            System.out.println("### History Event: " + event.toString());
            msg += event.toString() + '\n';
        }
        someText.setText(msg);
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

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void loadEvents(View view) {
        System.out.println("### day: " + eventsDay);
        System.out.println("### month: " + eventsMonth);
        presenter = new EventsPresenter(new EventsModel(eventsMonth, eventsDay), this);
    }
}
