package com.vithack.tutorfinder.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vithack.tutorfinder.R;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.feed_card_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name_text_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_text_card = itemView.findViewById(R.id.name_text_card);
        }
    }
}
