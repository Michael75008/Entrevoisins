package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

public class Room implements Serializable {

    private int id;

    private int image;

    private String name;

    public Room(int id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
