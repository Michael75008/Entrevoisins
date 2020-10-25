package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

public class Room implements Serializable {

    private long id;

    private int image;

    private String name;

    public Room(long id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Room(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
