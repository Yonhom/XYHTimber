package com.xuyonghong.xyhtimber.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuyonghong.xyhtimber.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * recycler view adapter for the song list recycler view
 * Created by xuyonghong on 2017/6/2.
 */

public class SongListRecyclerViewAdapter
        extends RecyclerView.Adapter<SongListRecyclerViewAdapter.SongListViewHolder> {

    @Override
    public SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item_flat_view, parent, false);
        return new SongListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class SongListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_art)
        ImageView songArt;
        @BindView(R.id.song_title)
        TextView songTitle;
        @BindView(R.id.artist_name)
        TextView artistName;

        public SongListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
