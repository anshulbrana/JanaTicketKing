package com.example.janaticketking;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    TextView eventDate, eventTime, eventPrice, eventVenue;
    ImageView eventPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        bindActivity();
    }

    private void bindActivity() {
        eventDate = (TextView) findViewById(R.id.eventDate);
        eventTime = (TextView) findViewById(R.id.eventTime);
        eventPrice = (TextView) findViewById(R.id.eventPrice);
        eventVenue = (TextView) findViewById(R.id.eventVenue);
        eventPhoto = (ImageView) findViewById(R.id.eventPhoto);


    }




}
