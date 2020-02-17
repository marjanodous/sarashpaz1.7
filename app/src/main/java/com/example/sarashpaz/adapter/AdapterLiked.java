package com.example.sarashpaz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.R;
import com.example.sarashpaz.ActivityTozihat;
import com.example.sarashpaz.model.LikedFoods;

import java.util.ArrayList;

public class AdapterLiked extends ArrayAdapter {

    public int resourceId;
    public Activity activity;
    public ArrayList<LikedFoods> data = new ArrayList<>();

    public static boolean flag_Liked = false;

    public AdapterLiked(Activity activity, int resourceId, ArrayList<LikedFoods> object) {
        super(activity, resourceId, object);
        this.resourceId = resourceId;
        this.activity = activity;
        this.data = object;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            grid = inflater.inflate(R.layout.item_menu, null);
            TextView textView = grid.findViewById(R.id.txt_itemmenu);
            ImageView imageView = grid.findViewById(R.id.img_itemmenu);
            LikedFoods likedFoods = new LikedFoods();
            likedFoods = data.get(position);
            textView.setText(likedFoods.getTitle());

            /*1.decode base64 string to image*/
            byte[] imageBytes = Base64.decode(likedFoods.getImg(), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(decodedImage);

            /*2.click item to show from tozihat*/
            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flag_Liked = true;
                    Intent intent = new Intent(activity, ActivityTozihat.class);
                    intent.putExtra("title_liked", data.get(position).getTitle());
                    intent.putExtra("image_liked", data.get(position).getImg());
                    intent.putExtra("mavad_liked", data.get(position).getMavad());
                    intent.putExtra("tahaieh_liked", data.get(position).getTahaieh());
                    activity.startActivity(intent);
                }
            });
        } else {
            grid = view;
        }
        return grid;
    }
}

