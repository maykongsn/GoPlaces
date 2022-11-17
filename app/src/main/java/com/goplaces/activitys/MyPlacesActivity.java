package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goplaces.R;
import com.goplaces.adapter.PlacesAdapter;
import com.goplaces.dao.PlacesDAO;
import com.goplaces.dao.PlacesDAOInterface;
import com.goplaces.model.Place;
import com.goplaces.model.Review;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class MyPlacesActivity extends AppCompatActivity {
    PlacesDAOInterface placesDAO;
    PlacesAdapter adapter;
    ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        placesDAO = PlacesDAO.getInstance(this);
        places = placesDAO.listPlaces();

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new PlacesAdapter(places);

        SwipeableRecyclerView recyclerViewPlaces = findViewById(R.id.recyclerViewPlaces);
        recyclerViewPlaces.setLayoutManager(linearLayoutManager);
        recyclerViewPlaces.setAdapter(adapter);

        recyclerViewPlaces.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                removePlace(places.get(position));
            }

            @Override
            public void onSwipedRight(int position) {
                editPlace(places.get(position));
            }
        });
    }

    public void addPlace(View view) {
        Intent intent = new Intent(this, FormPlaceActivity.class);
        startActivityForResult(intent, 1);
    }

    private void editPlace(Place place) {
        Intent intent = new Intent(this, FormPlaceActivity.class);

        intent.putExtra("id", ""+place.getId());
        intent.putExtra("city", place.getCity());
        intent.putExtra("country", place.getCountry());
        startActivityForResult(intent, 2);
    }

    public void removePlace(Place place) {
        placesDAO.removePlace(place);
        adapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            String city = data.getExtras().getString("city");
            String country = data.getExtras().getString("country");

            Place place = new Place(city, country);

            placesDAO.addPlace(place);
            adapter.notifyDataSetChanged();
        } else if(requestCode == 2 && resultCode == 1) {
            String idString = data.getExtras().getString("id");
            String city = data.getExtras().getString("city");
            String country = data.getExtras().getString("country");

            if(idString != null) {
                int id = Integer.parseInt(idString);

                Place place = new Place(city, country);
                place.setId(id);

                placesDAO.editPlace(place);
                adapter.notifyDataSetChanged();
            }
            adapter.notifyDataSetChanged();
        }
    }
}