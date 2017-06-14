package com.xuyonghong.xyhtimber.model;

/**
 * Created by xuyonghong on 2017/6/6.
 */

public class Album {
    private String album;
    private String artist;
    private String alnumArt;

    public Album(String album, String artist, String alnumArt) {
        this.album = album;
        this.artist = artist;
        this.alnumArt = alnumArt;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlnumArt() {
        return alnumArt;
    }

}
