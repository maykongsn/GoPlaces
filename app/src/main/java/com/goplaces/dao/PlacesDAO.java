package com.goplaces.dao;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;
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
        DatabaseReference placesReference = FirebaseHelper.getDatabaseReference()
                .child("places")
                .child(FirebaseHelper.getIdFirebase())
                .child(place.getId());
        placesReference.setValue(place);
        return true;
    }

    @Override
    public boolean editPlace(Place place) {
        DatabaseReference placesReference = FirebaseHelper.getDatabaseReference()
                .child("places")
                .child(FirebaseHelper.getIdFirebase())
                .child(place.getId());
        placesReference.setValue(place);
        return true;
    }

    @Override
    public ArrayList<Place> listPlaces() {
        return places;
    }

    @Override
    public boolean removePlace(Place place) {
        DatabaseReference placesReference = FirebaseHelper.getDatabaseReference()
                .child("places")
                .child(FirebaseHelper.getIdFirebase())
                .child(place.getId());
        placesReference.removeValue();
        return true;
    }
}