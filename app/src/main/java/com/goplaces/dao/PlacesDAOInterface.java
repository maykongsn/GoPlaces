package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Place;

import java.util.ArrayList;

public interface PlacesDAOInterface {
    static PlacesDAOInterface getInstance(Context context) {
        return null;
    }

    ArrayList<Place> listPlaces();
    boolean addPlace(Place place);
    boolean editPlace(Place place);
    boolean removePlace(Place place);
}
