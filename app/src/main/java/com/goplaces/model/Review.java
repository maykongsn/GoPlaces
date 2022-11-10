package com.goplaces.model;

public class Review {
    private String id;
    private String user_id;
    private String city;
    private String country;
    private String description;
    private float rating;
    private long publicationDate;

    public Review(String city, String country, String description, float rating) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

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

    @Override
    public String toString() {
        return "City: " + city;
    }
}
