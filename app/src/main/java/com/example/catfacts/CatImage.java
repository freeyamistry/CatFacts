package com.example.catfacts;

import java.lang.reflect.Array;

public class CatImage {

    private String imageId;
    private String imageUrl;

    public Array[] getBreeds() {
        return breeds;
    }

    public void setBreeds(Array[] breeds) {
        this.breeds = breeds;
    }

    public CatImage(){

    }
    public CatImage(String imageId, String imageUrl, Array[] breeds) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.breeds = breeds;
    }

    private Array breeds [];


    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}

