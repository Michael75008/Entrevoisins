package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

/**
 * Model object representing a Room
 */

public class Room implements Serializable {

    /**
     * Identifier
     */

    private long id;

    /**
     * Room color/image
     */

    private int mImage;

    /**
     * Name
     */

    private String mName;

    /**
     * Constructor
     */

    public Room(long id, int image, String name) {
        this.id = id;
        this.mImage = image;
        this.mName = name;
    }

    public Room() {}

    /**
     * Getters and setters
     */

    public int getImage() { return mImage; }

    public String getName() { return mName; }

    public void setName(String name) { this.mName = name; }

    public void setImage(int image) {
        this.mImage = image;
    }
}
