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
import com.xuyonghong.xyhtimber.adapter.SongListRecyclerViewAdapter;
import com.xuyonghong.xyhtimber.media.MediaManager;
import com.xuyonghong.xyhtimber.model.Song;

import java.util.List;

/**
 * page 1 of the view pager of MediaLibraryFragment
 * Created by xuyonghong on 2017/6/2.
 */

public class SongFragment extends Fragment {

    /**
     * the recycler view for displaying music in the library
     */
    private RecyclerView songList;

    /**
     * the array list for storing all the song info in the library
     */
    private List<Song> songs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get all the song info in the media lib
        songs = MediaManager.getInstance(getContext()).getMediaList();
        

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        songList = (RecyclerView) inflater.inflate(R.layout.fragment_song, container, false);
        // init the song list recyclerView
        songList.setLayoutManager(new LinearLayoutManager(getContext()));
        songList.setAdapter(new SongListRecyclerViewAdapter(songs));
        return songList;
    }
}
