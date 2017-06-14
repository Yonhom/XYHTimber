package com.xuyonghong.xyhtimber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.model.Song;

import java.util.List;

/**
 * Created by xuyonghong on 2017/6/7.
 */

public class AlbumSongAdapter extends RecyclerView.Adapter {

    private List<Song> songInAlbum;

    public AlbumSongAdapter(Context context, List<Song> songsInAlbum) {
        this.songInAlbum = songsInAlbum;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
