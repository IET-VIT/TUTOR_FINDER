package com.vithack.tutorfinder.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vithack.tutorfinder.R;

import butterknife.BindView;

public class NewsFeedFragment extends Fragment {

    @BindView(R.id.feed_recycler) RecyclerView feed_recycler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_feed, container, false);

        return root;
    }
}