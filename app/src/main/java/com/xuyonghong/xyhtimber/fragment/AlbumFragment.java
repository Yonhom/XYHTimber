package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.adapter.AlbumAdapter;
import com.xuyonghong.xyhtimber.model.Album;

/**
 * this fragment is for both Album and Artist page
 * Created by xuyonghong on 2017/6/5.
 */

public class AlbumFragment extends Fragment implements AlbumAdapter.OnAlbumItemClickedListener{
    /**
     * the recycler view for displaying album in the library
     */
    private RecyclerView albumGrid;

    private int numberOfColumn = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        albumGrid = (RecyclerView) inflater.inflate(R.layout.fragment_song_album_artist, container, false);
        // init the song list recyclerView
        albumGrid.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumn));
        AlbumAdapter adapter = new AlbumAdapter(getContext());
        // set the album fragment as the album adapter's delegate
        adapter.setAlbumItemClickedListener(this);
        albumGrid.setAdapter(adapter);
        return albumGrid;
    }


    /**
     * when the album item in the album fragment is clicked,
     * this delegate method is called
     * @param album
     */
    @Override
    public void onAlbumItemClicked(Album album) {
        // this is where the album item click action really happened
        FragmentManager supportFragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        // here we add the album detail fragment to the stack, so when it is
        // popped off the stack, the album fragment si shown again
        fragmentTransaction.add(R.id.fragment_container, fragment, AlbumDetailFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
