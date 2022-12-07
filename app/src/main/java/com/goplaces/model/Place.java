package com.goplaces.model;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;

public class Place {
    private String id;
    private String userId;
    private String city;
    private String country;

    public Place() {}

    public Place(String city, String country) {
        DatabaseReference placeReference = FirebaseHelper.getDatabaseReference();
        this.setId(placeReference.push().getKey());
        this.city = city;
        this.country = country;
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
}

