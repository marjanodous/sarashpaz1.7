package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.AdapterMenu;
import com.example.sarashpaz.adapter.AdapterLiked;
import com.example.sarashpaz.adapter.AdapterSearch;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_behtarin_dastoor_pokht_ha;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_img;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_myanvade;
import com.example.sarashpaz.db.Database;
import com.example.sarashpaz.model.LikedFoods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ActivityTozihat extends AppCompatActivity {

    /*create object for xml*/
    private TextView txtTitle, txtMavad, txtTahaieh;
    ImageView imgTozihat, imgLike,imgBack;

    public boolean flagLike = false;
    String encodedImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_tozihat);

        /* Get object from xml file*/
        txtMavad = findViewById(R.id.mavad_lazem);
        txtTahaieh = findViewById(R.id.tarz_tahie);
        txtTitle = findViewById(R.id.title_tozihat);
        imgTozihat = findViewById(R.id.img_tozihat);
        imgLike = findViewById(R.id.img_like);
        imgBack = findViewById(R.id.img_back_tozihat);

//        String title = txtTitle.getText().toString().trim();
//        LikedFoods foods = Database.selectliked(getApplicationContext(), title);
//        String name=foods.getTitle();
//        Log.i("b ::::::::::::::::",name);

//        if (name!= "") {
//            Log.i("b ::::::::::::::::",name);
//            imgLike.setColorFilter(getApplication().getResources().getColor(R.color.colorPrimaryDark));
//        }
//        else{
//            Log.i("b ::::::::::::::::",name);
//            imgLike.setColorFilter(getApplication().getResources().getColor(R.color.colorPrimaryLight));
//        }

        /*1.load from recycler_myanvade*/
        if (ItemAdapter_recycler_myanvade.flag_myanvade == true) {
            Bundle bundle = getIntent().getExtras();
            txtTitle.setText(bundle.getString("title_myanvade"));
            txtMavad.setText(bundle.getString("mavad_myanvade"));
            txtTahaieh.setText(bundle.getString("tahaieh_myanvade"));
            imgTozihat.setImageResource(bundle.getInt("image_myanvade"));
        }
        /*1.load from behtarin_dastoor_pokht_ha*/
        else if (ItemAdapter_recycler_behtarin_dastoor_pokht_ha.flag_behtarin_dastoor_pokht_ha == true) {
            Bundle bundle = getIntent().getExtras();
            txtTitle.setText(bundle.getString("title_bdph"));
            txtMavad.setText(bundle.getString("mavad_bdph"));
            txtTahaieh.setText(bundle.getString("tahaieh_bdph"));
            imgTozihat.setImageResource(bundle.getInt("image_bdph"));
        }
        /*1.load from AdapterSearch*/
        else if (AdapterSearch.flag_serch == true) {
            Bundle bundle = getIntent().getExtras();
            txtTitle.setText(bundle.getString("title_search"));
            imgTozihat.setImageResource(bundle.getInt("img_search"));
            txtMavad.setText(bundle.getString("mavad_search"));
            txtTahaieh.setText(bundle.getString("tahaieh_search"));
        }
        /*1.load from ActivityLiked*/
        else if (AdapterLiked.flag_Liked == true) {
            flagLike = true;
            imgLike.setColorFilter(getApplication().getResources().getColor(R.color.colorPrimaryDark));
            Bundle bundle = getIntent().getExtras();
            txtTitle.setText(bundle.getString("title_liked"));
            //decode base64 string to image
            byte[] imageBytes = Base64.decode(bundle.getString("image_liked"), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imgTozihat.setImageBitmap(decodedImage);
            txtMavad.setText(bundle.getString("mavad_liked"));
            txtTahaieh.setText(bundle.getString("tahaieh_liked"));
        }
        /*1.load from Activitygroup*/
        else if (AdapterMenu.flag_group == true) {
            Bundle bundle = getIntent().getExtras();
            txtTitle.setText(bundle.getString("title_food"));
            txtMavad.setText(bundle.getString("mavad_food"));
            txtTahaieh.setText(bundle.getString("tahaieh_food"));
            imgTozihat.setImageResource(bundle.getInt("image_food"));
        }

        /*6.calling method liked or disliked food*/
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagLike == false) {
                    imgLike.setColorFilter(getApplication().getResources().getColor(R.color.colorPrimaryDark));
                    flagLike = true;
                    /*get image bitmap to send database*/
                    imgTozihat.buildDrawingCache();
                    Bitmap bmap = imgTozihat.getDrawingCache();
                    encodedImageData = getEncoded64ImageStringFromBitmap(bmap);
                    foodsLiked();
                } else {
                    imgLike.setColorFilter(getApplication().getResources().getColor(R.color.colorPrimaryLight));
                    flagLike = false;
                    foodsDisLiked();
                }
            }
        });
        /*back and this finish*/
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*2.methid to addliked food*/
    public void foodsLiked() {
        String tahaieh = txtTahaieh.getText().toString().trim();
        String mavad = txtMavad.getText().toString().trim();
        String title = txtTitle.getText().toString().trim();
        Database.addLiked(title, true, encodedImageData, mavad, tahaieh, getApplicationContext());
    }

    /*3.methid to disliked food*/
    public void foodsDisLiked() {
        String title = txtTitle.getText().toString().trim();
        Database.delLiked(getApplicationContext(), title);
    }
    /*4.set false value for flag adapters to back*/
    @Override
    public void onBackPressed() {
        ItemAdapter_recycler_myanvade.flag_myanvade = false;
        ItemAdapter_recycler_behtarin_dastoor_pokht_ha.flag_behtarin_dastoor_pokht_ha = false;
        ItemAdapter_recycler_img.flag_img = false;
        AdapterSearch.flag_serch = false;
        AdapterMenu.flag_group = false;
        AdapterLiked.flag_Liked = false;
        super.onBackPressed();
    }
    /*5.Encoded64 Image String From Bitmap*/
    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

}
