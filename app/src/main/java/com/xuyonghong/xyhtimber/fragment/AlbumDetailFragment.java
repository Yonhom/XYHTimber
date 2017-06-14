package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.media.MediaManager;
import com.xuyonghong.xyhtimber.model.Song;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/7.
 */

public class AlbumDetailFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toobar;

    @BindView(R.id.app_bar_image)
    ImageView appBarImage;

    @BindView(R.id.song_list_in_album)
    RecyclerView songListInAlbumView;

    private List<Song> songsInAlbum;

    /**
     * return a album detail fragment with arguments from the
     * class calling this method
     * @param albumId
     * @return
     */
    public static AlbumDetailFragment newInstance(long albumId) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        // this designate which album's detail info to show
        args.putLong(MediaStore.Audio.Albums.ALBUM_ID, albumId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // populate the song list of the designated album
        songsInAlbum = MediaManager.getInstance(getContext())
                .getSongList(String.valueOf(
                        getArguments().getLong(MediaStore.Audio.Albums.ALBUM_ID)));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_detail, container, false);
        ButterKnife.bind(this, view);

        // init the toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toobar);

        //populate the album song list
        songListInAlbumView.setLayoutManager(new LinearLayoutManager(getContext()));
//        songListInAlbumView.setAdapter(new AlbumSongAdapter(getContext(), songsInAlbum));

        return view;
    }
}
