package com.goplaces.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goplaces.R;
import com.goplaces.model.Friend;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{
    private ArrayList<Friend> dataSet;

    public FriendsAdapter(ArrayList<Friend> dataSet) {
        this.dataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewCity);
        }

        public TextView getTextViewUsername() {
            return textViewUsername;
        }
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.friend_item, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        Friend friend = dataSet.get(position);

        holder.getTextViewUsername().setText(friend.getUsername());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
