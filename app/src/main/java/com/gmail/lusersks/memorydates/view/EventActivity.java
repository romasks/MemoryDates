package com.gmail.lusersks.memorydates.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.lusersks.memorydates.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.event_name_2)
    TextView eventName;

    @BindView(R.id.event_pic)
    ImageView eventImage;

    @BindView(R.id.event_description_2)
    TextView eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        setTitle("Event");

        Intent intent = getIntent();
        eventName.setText(intent.getStringExtra("name"));
        eventDescription.setText(intent.getStringExtra("description"));
        Picasso.with(this).load(intent.getStringExtra("image")).into(eventImage);
    }
}
