package com.goplaces.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goplaces.R;
import com.goplaces.model.Place;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    private ArrayList<Place> dataSet;

    public PlacesAdapter(ArrayList<Place> dataSet) {
        this.dataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCity;
        TextView textViewCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewCountry = itemView.findViewById(R.id.textViewCountry);
        }

        public TextView getTextViewCity() {
            return textViewCity;
        }

        public TextView getTextViewCountry() {
            return textViewCountry;
        }
    }

    @NonNull
    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.place_item, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.ViewHolder holder, int position) {
        Place place = dataSet.get(position);

        holder.getTextViewCity().setText(place.getCity());
        holder.getTextViewCountry().setText(place.getCountry());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
