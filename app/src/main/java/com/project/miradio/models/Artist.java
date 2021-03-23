package com.project.miradio.models;

import android.app.NotificationChannel;

import java.io.Serializable;
import java.util.List;

public class Artist implements Serializable {
    private String artist;
    List<Albums> albums;

    public Artist(String artist, List<Albums> albums) {
        this.artist = artist;
        this.albums = albums;
    }

    public int albumSize(){
        return albums.size();
    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }
}
