package com.example.janaticketking;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class SlidingImageAdaptor extends PagerAdapter {


    ArrayList<EventModel> events = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public SlidingImageAdaptor(ArrayList<EventModel> events, Context context) {
        this.events = events;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return events.size();


    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        Glide.with(context)
                .load(events.get(position).getImageURl())
                .into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }



}