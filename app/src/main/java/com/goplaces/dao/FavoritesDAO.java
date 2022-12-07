package com.goplaces.dao;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Favorite;

public class FavoritesDAO implements FavoritesDAOInterface {
    private static Context context;
    private static FavoritesDAO favoritesDAO = null;
    private FavoritesDAO(Context context) {
        favoritesDAO.context = context;
    }

    public static FavoritesDAOInterface getInstance(Context context) {
        if(favoritesDAO == null) {
            favoritesDAO = new FavoritesDAO(context);
        }
        return favoritesDAO;
    }

    @Override
    public boolean addFavorite(Favorite favorite) {
        DatabaseReference favoritesReference = FirebaseHelper.getDatabaseReference()
                .child("favorites")
                .child(FirebaseHelper.getIdFirebase());
        favoritesReference.setValue(favorite.getFavorites());
        return true;
    }
}
