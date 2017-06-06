package com.xuyonghong.xyhtimber.model;

/**
 * Created by xuyonghong on 2017/6/6.
 */

public class Artist {
    private long albumCount;
    private long trackCount;
    private String artistKey;
    private String artistName;

    public Artist(long albumCount, long trackCount, String artistKey, String artistName) {
        this.albumCount = albumCount;
        this.trackCount = trackCount;
        this.artistKey = artistKey;
        this.artistName = artistName;
    }

    public long getAlbumCount() {
        return albumCount;
    }

    public long getTrackCount() {
        return trackCount;
    }

    public String getArtistKey() {
        return artistKey;
    }

    public String getArtistName() {
        return artistName;
    }
}
