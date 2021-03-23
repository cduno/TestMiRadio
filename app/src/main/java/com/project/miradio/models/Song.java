package com.project.miradio.models;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;

    public Song(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
