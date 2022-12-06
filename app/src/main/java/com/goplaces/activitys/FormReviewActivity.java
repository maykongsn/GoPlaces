package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.storage.StorageReference;
import com.goplaces.R;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Image;
import com.goplaces.model.Review;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormReviewActivity extends AppCompatActivity {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    private EditText editTextCity;
    private EditText editTextCountry;
    private EditText editTextDescription;
    private RatingBar ratingBar;

    private ArrayList<Image> images = new ArrayList<>();

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
        image1.setOnClickListener(view -> showBottomSheet(0));

        image2.setOnClickListener(view -> showBottomSheet(1));

        image3.setOnClickListener(view -> showBottomSheet(2));
    }

    public void showBottomSheet(int requestCode) {
        View bottomSheet = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();

        bottomSheet.findViewById(R.id.btnGallery).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            checkPermissionGallery(requestCode);
        });
    }

    public void upload(int requestCode, String source) {
        Image image = new Image(source, requestCode);
        if(images.size() > 0) {
            boolean find = false;
            for(int i = 0; i < images.size(); i++) {
                if(images.get(i).getIndex() == requestCode) {
                    find = true;
                }
            }
            if(find) {
                images.set(requestCode, image);
            } else {
                images.add(image);
            }
        } else {
            images.add(image);
        }
    }

    public void checkPermissionGallery(int requestCode) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, requestCode);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bitmap1;
            Bitmap bitmap2;
            Bitmap bitmap3;

            Uri selectedImage = data.getData();
            String source;
            if(requestCode <= 2) {
                try {
                    source = selectedImage.toString();
                    switch (requestCode) {
                        case 0:
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                            } else {
                                ImageDecoder.Source decoderSource = ImageDecoder.createSource(getContentResolver(), selectedImage);
                                bitmap1 = ImageDecoder.decodeBitmap(decoderSource);
                            }
                            image1.setImageBitmap(bitmap1);
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                            } else {
                                ImageDecoder.Source decoderSource = ImageDecoder.createSource(getContentResolver(), selectedImage);
                                bitmap2 = ImageDecoder.decodeBitmap(decoderSource);
                            }
                            image2.setImageBitmap(bitmap2);
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                            } else {
                                ImageDecoder.Source decoderSource = ImageDecoder.createSource(getContentResolver(), selectedImage);
                                bitmap3 = ImageDecoder.decodeBitmap(decoderSource);
                            }
                            image3.setImageBitmap(bitmap3);
                            break;
                    }
                    upload(requestCode, source);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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