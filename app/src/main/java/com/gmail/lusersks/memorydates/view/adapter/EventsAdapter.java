package com.gmail.lusersks.memorydates.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.lusersks.memorydates.R;
import com.gmail.lusersks.memorydates.entity.HistoryEvent;
import com.gmail.lusersks.memorydates.view.EventActivity;
import com.gmail.lusersks.memorydates.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private final List<HistoryEvent> list = new ArrayList<>();
    private final Activity activity;

    public EventsAdapter(Activity activity) {
        System.out.println("### EventsAdapter constructor");
        this.activity = activity;
    }

    @Override
    public EventsAdapter.EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("### onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_events, parent, false);
        return new EventsAdapter.EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.EventsViewHolder holder, int position) {
        System.out.println("### onBindViewHolder");
        HistoryEvent event = list.get(position);
        holder.name.setText(event.getName());
        holder.description.setText(event.getDescription());
        holder.type.setText(event.getType());
        Picasso.with(activity).load(event.getImage()).into(holder.image);

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, EventActivity.class);
            intent.putExtra("name", event.getName());
            intent.putExtra("image", event.getImage());
            intent.putExtra("description", event.getDescription());
            activity.startActivity(intent);
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<HistoryEvent> getData() {
        return list;
    }

    public void setData(List<HistoryEvent> data) {
        list.clear();
        list.addAll(data);
        System.out.println("### list: " + list);
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        TextView  name, description, type;
        ImageView image;
        CardView  cardView;

        EventsViewHolder(View view) {
            super(view);
            cardView    = view.findViewById(R.id.events_card_view);
            name        = view.findViewById(R.id.event_name);
            description = view.findViewById(R.id.event_description);
            type        = view.findViewById(R.id.event_type);
            image       = view.findViewById(R.id.event_image);
        }
    }
}
