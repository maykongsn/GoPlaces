package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.goplaces.R;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Review;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class FormReviewActivity extends AppCompatActivity {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    private EditText editTextCity;
    private EditText editTextCountry;
    private EditText editTextDescription;
    private RatingBar ratingBar;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review);
        id = -1;

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        editTextCity = findViewById(R.id.editTextCity);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextDescription = findViewById(R.id.editTextDescription);
        ratingBar = findViewById(R.id.ratingBar);

        if(getIntent().getExtras() != null) {
            String idString = getIntent().getExtras().getString("id");
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");
            String description = getIntent().getExtras().getString("description");
            float rating = getIntent().getExtras().getFloat("rating");

            if(idString != null) {
                id = Integer.parseInt(idString);
            }

            editTextCity.setText(city);
            editTextCountry.setText(country);
            editTextDescription.setText(description);
            ratingBar.setRating(rating);
        }

        openBottomSheet();

        textViewToolbar.setText("Novo Review");
    }

    public void openBottomSheet() {
        image1.setOnClickListener(view -> showBottomSheet(1));

        image2.setOnClickListener(view -> showBottomSheet(2));

        image3.setOnClickListener(view -> showBottomSheet(3));
    }

    public void showBottomSheet(int position) {
        View bottomSheet = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();

        bottomSheet.findViewById(R.id.btnCamera).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            checkPermissionCamera(position);
        });

        bottomSheet.findViewById(R.id.btnGallery).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            checkPermissionGallery(position);
        });
    }

    public void checkPermissionCamera(int position) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedTitle("Permissão Negada")
                .setDeniedMessage("Aceite a permissão para tirar uma foto com a câmera.")
                .setGotoSettingButtonText("Sim")
                .setDeniedCloseButtonText("Não")
                .setPermissions(Manifest.permission.CAMERA)
                .check();
    }

    public void checkPermissionGallery(int position) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedTitle("Permissão Negada")
                .setDeniedMessage("Aceite a permissão para selecionar uma imagem na galeria.")
                .setGotoSettingButtonText("Sim")
                .setDeniedCloseButtonText("Não")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    public void addReview(View view) {
        String city = editTextCity.getText().toString();
        String country = editTextCountry.getText().toString();
        String description = editTextDescription.getText().toString();
        float rating = ratingBar.getRating();

        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("description", description);
        intent.putExtra("rating", rating);

        if( id >= 0 ) {
            intent.putExtra("id", "" + id);
        }
        setResult(1, intent);
        finish();
    }

}