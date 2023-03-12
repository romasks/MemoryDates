package com.gmail.lusersks.memorydates.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.lusersks.memorydates.R;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;
import com.gmail.lusersks.memorydates.model.EventsModel;
import com.gmail.lusersks.memorydates.presenter.EventsPresenter;
import com.gmail.lusersks.memorydates.view.adapter.EventsAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.events_list)
    RecyclerView eventsList;

    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private String eventsDay;
    private String eventsMonth;

    private Calendar date;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        setInitialDate();
    }

    private void setInitialDate() {
        date = Calendar.getInstance();
        eventsDay = (date.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        eventsMonth = (date.get(Calendar.MONTH) < 9 ? "0" : "") + String.valueOf(date.get(Calendar.MONTH) + 1);

        dateSetListener = (datePicker, year, monthOfYear, dayOfMonth) -> {
            System.out.println("### day: " + dayOfMonth);
            System.out.println("### month: " + monthOfYear);
            eventsDay = (dayOfMonth < 10 ? "0" : "") + String.valueOf(dayOfMonth);
            eventsMonth = (monthOfYear < 9 ? "0" : "") + String.valueOf(monthOfYear + 1);
            loadEvents();
        };
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_calendar:
                new DatePickerDialog(MainActivity.this, dateSetListener,
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            default:
                makeToast("Unknown ID");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showList(List<HistoryEvent> eventsList) {
        System.out.println("### eventsList: " + eventsList);

        eventsAdapter.setData(eventsList);
        recyclerView.setAdapter(eventsAdapter);
    }

    @Override
    public void showError(String error) {
        eventsAdapter.setData(new ArrayList<>());
        recyclerView.setAdapter(eventsAdapter);

        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        eventsAdapter.setData(new ArrayList<>());
        recyclerView.setAdapter(eventsAdapter);

        makeToast(getString(R.string.empty_events_list));
    }

    @Override
    public List<HistoryEvent> getHistoryEvents() {
        return eventsAdapter.getData();
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    public void loadEvents(View view) {
    public void loadEvents() {
        System.out.println("### day: " + eventsDay);
        System.out.println("### month: " + eventsMonth);
        new EventsPresenter(new EventsModel(eventsMonth, eventsDay), this).loadEvents();
    }
}
