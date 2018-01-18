package com.gmail.lusersks.memorydates.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.lusersks.memorydates.R;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private final List<HistoryEvent> list;
    private final Activity activity;

    public EventsAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_events, parent, false);
        return new EventsAdapter.EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        HistoryEvent event = list.get(position);
        holder.name.setText(event.getName());
        holder.description.setText(event.getDescription());
        holder.type.setText(event.getType());

        holder.cardView.setOnClickListener(v -> {
            Uri uri = Uri.parse(list.get(position).getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<HistoryEvent> getData() {
        return list;
    }

    public void setList(List<HistoryEvent> data) {
        list.addAll(data);
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, type;
        CardView cardView;

        EventsViewHolder(View view) {
            super(view);
            cardView    = (CardView) view.findViewById(R.id.events_card_view);
            name        = (TextView) view.findViewById(R.id.event_name);
            description = (TextView) view.findViewById(R.id.event_description);
            type        = (TextView) view.findViewById(R.id.event_type);
        }
    }
}
