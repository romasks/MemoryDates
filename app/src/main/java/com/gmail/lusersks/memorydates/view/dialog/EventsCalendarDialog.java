package com.gmail.lusersks.memorydates.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CalendarView;

import com.gmail.lusersks.memorydates.R;

import butterknife.BindView;

public class EventsCalendarDialog extends DialogFragment {

    @BindView(R.id.dlg_calendar_view)
    CalendarView dlgCalendar;

    DialogCalendarActionListener mListener;
    private String eventsDay;
    private String eventsMonth;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DialogCalendarActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DialogCalendarActionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*dlgCalendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            System.out.println("### day: " + dayOfMonth);
            System.out.println("### month: " + month);
            eventsDay = String.valueOf(dayOfMonth);
            eventsMonth = (month < 10 ? "0" : "") + String.valueOf(month + 1);
        });*/

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_calendar, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogPositiveClick(EventsCalendarDialog.this, eventsDay, eventsMonth);
                    }
                });

        return builder.create();
    }
}
