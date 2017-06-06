package com.xuyonghong.xyhtimber.model;

/**
 * Created by xuyonghong on 2017/6/2.
 */

public class Song {
    private String title;
    private String artist;
    private long songId;
    /**
     * album id for constructing album art uri, which is used for loading
     * local album image from local media library
     */
    private long albumId;

    public Song(String title, String artist, long songId, long albumId) {
        this.title = title;
        this.artist = artist;
        this.songId = songId;
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public long getSongId() {
        return songId;
    }

    public long getAlbumId() {
        return albumId;
    }
}
