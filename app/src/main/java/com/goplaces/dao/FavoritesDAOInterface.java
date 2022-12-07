package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Favorite;

public interface FavoritesDAOInterface {
    static FavoritesDAOInterface getInstance(Context context) { return null; }

    boolean addFavorite(Favorite favorite);
}
