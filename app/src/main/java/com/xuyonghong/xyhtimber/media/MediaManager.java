package com.xuyonghong.xyhtimber.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.xuyonghong.xyhtimber.model.Album;
import com.xuyonghong.xyhtimber.model.Artist;
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
     * return the song cursor from the media library, which contains
     * as much as data we need
     * @return
     */
    public Cursor getCursorForPath(Uri path) {
        ContentResolver contentResolver = context.getContentResolver();
        // cursor containing the song list
        Cursor cursor = contentResolver.query(path, null, null, null, null);
        return cursor;
    }

    /**
     * get a media list, in this case: a song list
     * @return
     */
    public List<Song> getSongList() {
        List<Song> songs = new ArrayList<>();

        Cursor cursor = getCursorForPath(MediaPath.SONG_PATH);
        if (cursor != null && cursor.moveToFirst()) {
            // get the column name that we need
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
            int idIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID);
            int albumIdIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID);
            do {
                String title = cursor.getString(titleIndex);
                String artist = cursor.getString(artistIndex);
                long songId = cursor.getLong(idIndex);
                long albumID = cursor.getLong(albumIdIndex);

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
     * get the album list in the system's external storage media library
     * @return
     */
    public List<Album> getAlbumList() {
        List<Album> albums = new ArrayList<>();

        Cursor cursor = getCursorForPath(MediaPath.ALBUM_PATH);
        if (cursor != null && cursor.moveToFirst()) {

            // get the column name that we need
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ARTIST);
            // the column index of the album art's absolute path
            int albumArtIndex = cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
            do {
                String title = cursor.getString(titleIndex);
                String artist = cursor.getString(artistIndex);
                //  the art's absolute path
                String albumArt = cursor.getString(albumArtIndex);

                // create a song instance for each data in the cursor
                Album song = new Album(title, artist, albumArt);
                // add the song in list
                albums.add(song);

            } while (cursor.moveToNext());

            // close the cursor
            cursor.close();
        }

        return albums;
    }

    /**
     * return the data for the artist page
     * @return
     */
    public List<Artist> getArtistList() {
        List<Artist> artists = new ArrayList<>();

        Cursor cursor = getCursorForPath(MediaPath.ARTIST_PATH);
        if (cursor != null && cursor.moveToFirst()) {

            // get the column name that we need
            int artistNameIndex = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
            int artistKeyIndex = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST_KEY);
            int albumCountIndex = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
            int trackCountIndex = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);

            do {
                String name = cursor.getString(artistNameIndex);
                // TODO: get the artist's image, the line below is not work!!
                String artistArt = cursor.getString(artistKeyIndex);
                long albumCount = cursor.getLong(albumCountIndex);
                long trackCount = cursor.getLong(trackCountIndex);

                Artist artist = new Artist(albumCount, trackCount, artistArt, name);
                // add the artist in list
                artists.add(artist);

            } while (cursor.moveToNext());

            // close the cursor
            cursor.close();
        }

        return artists;
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
