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

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.MyViewHolder> {
    ArrayList<EventModel> category = new ArrayList<>();
    Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdaptor(ArrayList<EventModel> category, Context context) {
        this.category = category;
        this.context = context;
    }


    @NonNull
    @Override
    public CategoryAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final EventModel currentModel = category.get(i);

        Log.d("title", currentModel.imageURl);

        myViewHolder.eventTitle.setText(currentModel.getTitle());
        myViewHolder.eventDescription.setText(currentModel.getDescription());

        Glide.with(context)
                .load(category.get(i).getImageURl())
                .into(myViewHolder.bannerImage);

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CategoryActivity.class);
                i.putExtra(Constant.EVENTCONSTANT, currentModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView bannerImage;
        TextView eventTitle, eventDescription;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerImage = (ImageView) itemView.findViewById(R.id.categoryBannerImage);
            eventTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
            eventDescription = (TextView) itemView.findViewById(R.id.categoryDescription);
            cardView = (CardView) itemView.findViewById(R.id.categoryCardView);
        }
    }
}