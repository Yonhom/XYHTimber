package com.xuyonghong.xyhtimber.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.media.MediaManager;
import com.xuyonghong.xyhtimber.model.Song;
import com.xuyonghong.xyhtimber.service.MusicService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * recycler view adapter for the song list recycler view
 * Created by xuyonghong on 2017/6/2.
 */

public class SongAdapter
        extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    /**
     * the array list for storing all the song info in the library
     */
    private List<Song> songs;

    private MusicService musicService;

    private Intent playIntent;

    private boolean musicServiceBound;

    private Context context;

    public static final String ALBUM_PATH = "content://media/external/audio/albumart";

    /**
     interface callbacks for the service that is connected/disconnected from
     activity/fragment
     */
    private ServiceConnection musicConnection = new ServiceConnection() {
        /**
         * 3. this method is called after the bindService method is called in the fragment
         * @param name
         * @param service the binder here is a communication channel to the service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            // get the service
            musicService = binder.getService();
            // pass in the music list
            musicService.setSongList(songs);

            musicServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicServiceBound = false;
        }
    };

    public SongAdapter(Context context) {
        this.context = context;
        // get all the song info in the media lib
        songs = MediaManager.getInstance(context).getSongList();

        // start the music service when the fragment is started
        if (playIntent == null) {
            playIntent = new Intent(context, MusicService.class);
            // 1. connect the fragment to the service, create the service if needed
            // after this method is called the ServiceConnection interface callback is called
            context.bindService(
                    playIntent, musicConnection, Context.BIND_AUTO_CREATE);

            context.startService(playIntent);
        }
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song_flat_view, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, final int position) {
        holder.songTitle.setText(songs.get(position).getTitle());
        holder.artistName.setText(songs.get(position).getArtist());
        // get the art image with art id;
        holder.songArt.setImageBitmap(
                MediaManager.getInstance(context)
                        .getArtImageBitmapWithId(
                                ALBUM_PATH, songs.get(position).getAlbumId()));

        // set a onclick listener for the view this viewholder holds
        // play the specified song in the specified position
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.setSong(position);
                musicService.playSong();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_art)
        ImageView songArt;
        @BindView(R.id.song_title)
        TextView songTitle;
        @BindView(R.id.artist_name)
        TextView artistName;

        View view;

        public SongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        /**
         *
         * @return the view this view holder represents
         */
        public View getView() {
            return view;
        }
    }


}
