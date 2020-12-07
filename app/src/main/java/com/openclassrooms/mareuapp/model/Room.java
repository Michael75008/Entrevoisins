package com.openclassrooms.mareuapp.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Model object representing a Room
 */

public class Room implements Serializable {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Identifier
     */

    private long id;


    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Room color/image
     */

    private int image;

    /**
     * Name
     */

    private String name;

    /**
     * Constructor
     * @param id
     * @param image
     * @param name
     */

    public Room(long id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Room() {}

    /**
     * Getters and setters
     * @return
     */

    public int getImage() { return image; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
