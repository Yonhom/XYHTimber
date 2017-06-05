package com.xuyonghong.xyhtimber.model;

/**
 * Created by xuyonghong on 2017/6/2.
 */

public class Song {
    private String title;
    private String artist;
    private long songId;

    public Song(String title, String artist, long songId) {
        this.title = title;
        this.artist = artist;
        this.songId = songId;
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
}
