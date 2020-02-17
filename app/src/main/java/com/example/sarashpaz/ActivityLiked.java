package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.AdapterLiked;
import com.example.sarashpaz.db.Database;
import com.example.sarashpaz.model.LikedFoods;

import java.util.ArrayList;

public class ActivityLiked extends AppCompatActivity {

    /*create object for xml*/
    GridView gridView;
    ImageView imgBackHome;
    TextView txtTitle;

    public ArrayList<LikedFoods> likedFoods ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /* Get object from xml file*/
        txtTitle = findViewById(R.id.txttitledastehbandi);
        txtTitle.setText("علاقه مندی ها");
        imgBackHome = findViewById(R.id.img_backdastehbandi);
        gridView= findViewById(R.id.grid_menu);


        /*1.back to dastebandi*/
        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*2.set adapter to gridview */
        likedFoods = new ArrayList<>();
        likedFoods=Database.getliked(getApplicationContext());
        AdapterLiked adapter = new AdapterLiked(ActivityLiked.this, R.layout.item_menu, likedFoods);
        gridView.setAdapter(adapter);

    }
    /*3.to change grid items when you disliked foods*/
    @Override
    protected void onResume() {
        /*set adapter to gridview */
        likedFoods = new ArrayList<>();
        likedFoods=Database.getliked(getApplicationContext());
        AdapterLiked adapter = new AdapterLiked(ActivityLiked.this, R.layout.item_menu, likedFoods);
        gridView.setAdapter(adapter);
        super.onResume();
    }
}
