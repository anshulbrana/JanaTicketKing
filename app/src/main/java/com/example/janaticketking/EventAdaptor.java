package com.example.janaticketking;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdaptor extends RecyclerView.Adapter<EventAdaptor.MyViewHolder> {
    ArrayList<EventModel> events = new ArrayList<>();
    Context context;

    public EventAdaptor(ArrayList<EventModel> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final EventModel currentModel = events.get(i);

        Log.d("title", currentModel.imageURl);

        myViewHolder.eventTitle.setText(currentModel.getTitle());
        myViewHolder.eventDescription.setText(currentModel.getDescription());

        Glide.with(context)
                .load(events.get(i).getImageURl())
                .into(myViewHolder.bannerImage);

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventActivity.class);
                i.putExtra(Constant.EVENTCONSTANT, currentModel);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView bannerImage;
        TextView eventTitle, eventDescription;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerImage = (ImageView) itemView.findViewById(R.id.bannerImage);
            eventTitle = (TextView) itemView.findViewById(R.id.eventTitle);
            eventDescription = (TextView) itemView.findViewById(R.id.eventDescription);
            cardView = (CardView) itemView.findViewById(R.id.eventCardView);
        }
    }
}
