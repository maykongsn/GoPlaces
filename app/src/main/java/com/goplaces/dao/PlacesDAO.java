package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Place;

import java.util.ArrayList;

public class PlacesDAO implements PlacesDAOInterface {
    ArrayList<Place> places = new ArrayList<>();
    private static Context context;
    private static PlacesDAO placesDAO = null;

    private PlacesDAO(Context context) {
        PlacesDAO.context = context;
    }

    public static PlacesDAOInterface getInstance(Context context) {
        if(placesDAO == null) {
            placesDAO = new PlacesDAO(context);
        }
        return placesDAO;
    }

    @Override
    public boolean addPlace(Place place) {
        places.add(place);
        return true;
    }

    @Override
    public boolean editPlace(Place place) {
        for(Place newPlace : places) {
            if(newPlace.getId() == place.getId()) {
                newPlace.setCity(place.getCity());
                newPlace.setCountry(place.getCountry());
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Place> listPlaces() {
        return places;
    }

    @Override
    public boolean removePlace(Place place) {
        for(Place temp : places) {
            if(temp.getId() == place.getId()) {
                places.remove(place);
                return true;
            }
        }
        return false;
    }
}