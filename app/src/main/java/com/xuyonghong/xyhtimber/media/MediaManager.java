package com.xuyonghong.xyhtimber.media;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.xuyonghong.xyhtimber.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class MediaManager {
    private static MediaManager mediaManager;
    private Context context;
    private MediaManager(Context context) {
        this.context = context;
    }

    public static MediaManager getInstance(Context context) {
        if (mediaManager == null)
            mediaManager = new MediaManager(context);
        return mediaManager;
    }

    /**
     * get a media list, in this case: a song list
     * @return
     */
    public List<Song> getMediaList() {
        List<Song> songs = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        // uri for song
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // cursor containing the song list
        Cursor cursor = contentResolver.query(songUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // get the column name that we need
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            do {
                String title = cursor.getString(titleIndex);
                String artist = cursor.getString(artistIndex);
                long songId = cursor.getLong(idIndex);

                // create a song instance for each data in the cursor
                Song song = new Song(title, artist, songId);
                // add the song in list
                songs.add(song);

            } while (cursor.moveToNext());

            // close the cursor
            cursor.close();
        }

        return songs;
    }
}
