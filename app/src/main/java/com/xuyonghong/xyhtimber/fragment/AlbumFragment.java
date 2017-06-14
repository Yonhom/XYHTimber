package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.adapter.AlbumAdapter;

/**
 * this fragment is for both Album and Artist page
 * Created by xuyonghong on 2017/6/5.
 */

public class AlbumFragment extends Fragment {
    /**
     * the recycler view for displaying album in the library
     */
    private RecyclerView albumGrid;

    private int numberOfColumn = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        albumGrid = (RecyclerView) inflater.inflate(
                R.layout.fragment_song_album_artist, container, false);
        // init the song list recyclerView
        albumGrid.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumn));
        AlbumAdapter adapter = new AlbumAdapter(getContext());
        albumGrid.setAdapter(adapter);
        return albumGrid;

    }

}
