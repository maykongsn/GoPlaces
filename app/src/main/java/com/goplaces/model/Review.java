package com.goplaces.model;

public class Review {
    static int geradorIds = -1;

    private int id;
    private String city;
    private String country;
    private double rating;

    public Review(String city, String country, double rating) {
        this.city = city;
        this.country = country;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "City: " + city;
    }
}
