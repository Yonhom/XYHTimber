package com.xuyonghong.xyhtimber.media;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by xuyonghong on 2017/6/6.
 */

public class MediaPath {
    /**
     * the uri path for songs in the media library
     */
    public static final Uri SONG_PATH = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    /**
     * the uri path for albums in the media library
     */
    public static final Uri ALBUM_PATH = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    /**
     * the uri path for artists in the media library
     */
    public static final Uri ARTIST_PATH = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
}
