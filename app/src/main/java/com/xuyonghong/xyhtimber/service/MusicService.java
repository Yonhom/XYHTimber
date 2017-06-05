package com.xuyonghong.xyhtimber.service;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xuyonghong.xyhtimber.model.Song;

import java.io.IOException;
import java.util.List;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class MusicService extends Service
        implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener{
    private static final String TAG = MusicService.class.getSimpleName();

    private MediaPlayer player;
    /**
     * songs to play
     */
    private List<Song> songs;
    /**
     * the position od the current playing song
     */
    private int currentSongPosition;

    /**
     * the music binder as a communication channel between fragment
     * and this service
     */
    private IBinder binder = new MusicBinder();

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

    /**
     * 2. after the bindService method is called in the SongFragment,
     * this method is called to return a binder as a communication channel
     * between the service and SongFragment
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    /**
     * we will access this class from activity/fragment to
     * get access to the music playing service instance
     */
    public class MusicBinder extends Binder {
        /**
         * @return the current service instance
         */
        public MusicService getService() {
            return MusicService.this;
        }

    }

//    -------------------method in this service to manipulate the media player-------------------

    public void playSong() {
        player.reset();
        // get a song info
        Song playingSong = songs.get(currentSongPosition);
        // get the song's id for look up the media lib
        long songId = playingSong.getSongId();
        // the playing song's uri
        Uri songUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId);
        try {
            player.setDataSource(getApplicationContext(), songUri);
        } catch (IOException e) {
            Log.e(TAG, "Error setting data source for media player");
        }

        // when the player is prepared, the onPrepared() callback is called
        // we can start playing music there
        player.prepareAsync();
    }

    /**
     * this method is for the song fragment to select a song
     * with designated song index
     * @param songIndex
     */
    public void setSong(int songIndex) {
        currentSongPosition = songIndex;
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
        // start the playback
        player.start();
    }
}
