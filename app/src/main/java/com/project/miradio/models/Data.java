package com.project.miradio.models;

import android.app.NotificationChannel;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    List<Artist> data;

    public Data(List<Artist> data) {
        this.data = data;
    }

    public List<Artist> getArtists() {
        return data;
    }

    public void setArtists(List<Artist> data) {
        this.data = data;
    }
}
