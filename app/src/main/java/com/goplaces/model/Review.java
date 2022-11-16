package com.goplaces.model;

public class Review {
    static int geradorIds = -1;

    private int id;
    private String city;
    private String country;
    private String description;
    private float rating;

    public Review(String city, String country, String description, float rating) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.rating = rating;
        geradorIds++;
        this.id = geradorIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
