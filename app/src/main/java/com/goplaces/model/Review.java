package com.goplaces.model;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;

public class Review {
    private String id;
    private String userId;
    private String city;
    private String country;
    private String description;
    private float rating;

    public Review() {
        DatabaseReference reviewReference = FirebaseHelper.getDatabaseReference();
        this.setId(reviewReference.push().getKey());
    }

    public Review(String userId, String city, String country, String description, float rating) {
        DatabaseReference reviewReference = FirebaseHelper.getDatabaseReference();
        this.setId(reviewReference.push().getKey());
        this.userId = userId;
        this.city = city;
        this.country = country;
        this.description = description;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

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
