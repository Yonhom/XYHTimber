package com.xuyonghong.xyhtimber.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.xuyonghong.xyhtimber.model.Song;

import java.io.IOException;
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
    public List<Song> getSongList() {
        List<Song> songs = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        // song path
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // cursor containing the song list
        Cursor cursor = contentResolver.query(songUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // get the column name that we need
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            do {
                String title = cursor.getString(titleIndex);
                String artist = cursor.getString(artistIndex);
                long songId = cursor.getLong(idIndex);
                long albumID = cursor.getLong(albumIndex);

                // create a song instance for each data in the cursor
                Song song = new Song(title, artist, songId, albumID);
                // add the song in list
                songs.add(song);

            } while (cursor.moveToNext());

            // close the cursor
            cursor.close();
        }

        return songs;
    }

    /**
     *
     * @param path the path this art is in
     * @param artId the specific art with the specific id
     */
    public Bitmap getArtImageBitmapWithId(String path, long artId) {
        Bitmap bitmap = null;
        // get image uri with id
        Uri uri = ContentUris.withAppendedId(Uri.parse(path), artId);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
