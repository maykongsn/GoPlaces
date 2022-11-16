package com.goplaces.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goplaces.R;
import com.goplaces.model.Review;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private ArrayList<Review> dataSet;
    private OnClickListener onClickListener;

    public ReviewsAdapter(ArrayList<Review> dataSet, OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.dataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCity;
        TextView textViewCountry;
        RatingBar rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewCountry = itemView.findViewById(R.id.textViewCountry);
            rating = itemView.findViewById(R.id.ratingBar);
        }

        public TextView getTextViewCity() {
            return textViewCity;
        }

        public TextView getTextViewCountry() {
            return textViewCountry;
        }

        public RatingBar getRating() {
            return rating;
        }
    }

    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ViewHolder holder, int position) {
        Review review = dataSet.get(position);

        holder.getTextViewCity().setText(review.getCity());
        holder.getTextViewCountry().setText(review.getCountry());
        holder.getRating().setRating(review.getRating());
        holder.itemView.setOnClickListener(view -> onClickListener.onClick(review));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnClickListener {
        void onClick(Review review);
    }
}
