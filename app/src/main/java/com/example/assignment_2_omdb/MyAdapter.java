package com.example.assignment_2_omdb;

import android.content.ClipData;
import android.content.Context;
import android.media.RouteListingPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_2_omdb.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    List<MovieModel> items;
    Context context;

    public ItemClickListener clickListener;

    public MyAdapter(Context context, List<MovieModel> items) {
        this.context = context;
        this.items = items;
    }

    public void setClickListener(ItemClickListener myListener) {
        this.clickListener = myListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView, this.clickListener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MovieModel item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.year.setText(item.getYear());
        //holder.imageView.setImageResource(item.getPosterUrl());
        Picasso.get()
                .load(item.getPosterUrl())  // URL of the image
                .into(holder.imageView);  // ImageView where the image will be loaded
        holder.rating.setText(item.getRating());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
