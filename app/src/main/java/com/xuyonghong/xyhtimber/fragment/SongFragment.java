package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.adapter.SongAdapter;

/**
 * page 1 of the view pager of MediaLibraryFragment
 * Created by xuyonghong on 2017/6/2.
 */

public class SongFragment extends Fragment {

    /**
     * the recycler view for displaying music in the library
     */
    private RecyclerView songList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        songList = (RecyclerView) inflater.inflate(R.layout.fragment_song_album_artist, container, false);
        // init the song list recyclerView
        songList.setLayoutManager(new LinearLayoutManager(getContext()));
        songList.setAdapter(new SongAdapter(getContext()));
        return songList;
    }
}
