package com.example.assignment_2_omdb;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ItemClickListener clickListener;

    ImageView imageView;
    TextView title;
    TextView year;
    TextView rating;

    public MyViewHolder(@NonNull View itemView, ItemClickListener clickListener) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        title = itemView.findViewById(R.id.title_txt);
        year = itemView.findViewById(R.id.year_txt);
        rating = itemView.findViewById(R.id.type_txt);


        this.clickListener = clickListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "onViewHolder Click");
                clickListener.onClick(view, getAdapterPosition());
            }
        });

    }
}
