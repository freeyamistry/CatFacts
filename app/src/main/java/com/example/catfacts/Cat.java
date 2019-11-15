package com.example.catfacts;

import java.util.ArrayList;

public class Cat {

    public String catId;
    public String name;
    public String image;
    public String description;
    public String temperament;
    public String origin;
    public String life_span;
    public String weight;
    public String wikipedia_url;
    public int dog_friendly;
    public static ArrayList<Cat> favouritesList = new ArrayList<Cat>();


    public Cat() {

    }

    public Cat(String catId, String name, String image, String description, String weight, String temperament, String origin, String life_span, String wikipedia_url, int dog_friendly) {
        this.catId = catId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.weight = weight;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
        this.dog_friendly = dog_friendly;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
    }

    public String getImage() {
        return image;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setImage(String image) {
        this.image = image;
    }
}