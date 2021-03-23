package com.project.miradio.models;

import android.app.NotificationChannel;

import java.io.Serializable;
import java.util.List;

public class Albums implements Serializable {
    private String name;
    private String year;
    List<Song> songs;

    public Albums(String name, String year, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
