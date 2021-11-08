package com.example.newzer;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<NewsClass> newsList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<NewsClass> list) {
        this.context = context;
        this.newsList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsClass current = newsList.get(position);

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getUrl()));
            }
        });
        holder.mTitle.setText(current.getTitle());
        holder.mAuthor.setText("By " + anonymous(current.getAuthor()));


        if(current.getImageResourceId().contains("null")){
            holder.mImage.setImageResource(R.drawable.placeholder);
        }
        else{
        Glide.with(context)
                .load(current.getImageResourceId())
                .into(holder.mImage);
        }
        if(current.getDescription().contains("null")){
            holder.mDescription.setText("Click to view news");
        }else{
        holder.mDescription.setText(current.getDescription());}
        holder.mDate.setText(current.getDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    private String anonymous(String s) {
        if (s.contains("null")) {
            return "Anonymous";
        } else {
            return s;
        }
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthor, mTitle, mDescription, mDate;
        ImageView mImage;
        CardView mCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author_view);
            mTitle = itemView.findViewById(R.id.title_view);
            mDate = itemView.findViewById(R.id.date_view);
            mDescription = itemView.findViewById(R.id.description_view);
            mImage = itemView.findViewById(R.id.news_image);
            mCard = itemView.findViewById(R.id.card_view);
        }

    }
}