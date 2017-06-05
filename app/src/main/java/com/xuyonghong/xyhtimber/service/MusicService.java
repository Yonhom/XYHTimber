package com.xuyonghong.xyhtimber.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.xuyonghong.xyhtimber.model.Song;

import java.util.List;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class MusicService extends Service
        implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener{

    private MediaPlayer player;
    /**
     * songs to play
     */
    private List<Song> songs;
    /**
     * the position od the current playing song
     */
    private int currentSongPosition;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        initMusicPlayer();
    }

    private void initMusicPlayer() {
        // set the player properties
        // the wake lock will keep the player playing even though the phone is idle
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // set the call back listeners for media player
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    /**
     * the song list from activity or fragment
     * @param songList
     */
    public void setSongList(List<Song> songList) {
        songs = songList;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * we will access this class from activity/fragment to
     * get access to the music playing service instance
     */
    public class MusicBinder extends Binder {
        /**
         * @return the current service instance
         */
        MusicService getService() {
            return MusicService.this;
        }

    }

//    -----------------call backs for media player-------------------------

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
