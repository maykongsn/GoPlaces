package com.goplaces.model;

public class Place {
    static int geradorIds = -1;

    private int id;
    private String city;
    private String country;

    public Place(String city, String country) {
        this.city = city;
        this.country = country;
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
}

