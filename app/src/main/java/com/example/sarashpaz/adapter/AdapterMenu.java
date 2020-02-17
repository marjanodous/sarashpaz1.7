package com.example.sarashpaz.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarashpaz.R;
import com.example.sarashpaz.ActivityTozihat;
import com.example.sarashpaz.model.ItemRecycler;

import java.util.ArrayList;

public class AdapterMenu extends ArrayAdapter {

    public int resourceId;
    public Activity activity;
    public ArrayList<ItemRecycler> data;
    public static boolean flag_group=false;

    public AdapterMenu(Activity activity, int resourceId, ArrayList<ItemRecycler> object) {
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
            ItemRecycler foods = new ItemRecycler();
            foods = data.get(position);
            textView.setText(foods.getuName());
            imageView.setImageResource(foods.getuAvatar());
            /*1.click to show tozihat or AlertDialog*/
            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*2.get position you clicked*/
                    int pos=position;
//                    Toast.makeText(view.getContext(),Integer.toString(pos),Toast.LENGTH_LONG).show();

                   /*3.use position for show*/
                    if(pos<3){
                        flag_group=true;
                        Intent intent  = new Intent(activity, ActivityTozihat.class);
                        intent.putExtra("title_food",data.get(position).getuName());
                        intent.putExtra("image_food",data.get(position).getuAvatar());
                        intent.putExtra("mavad_food",data.get(position).getMavad());
                        intent.putExtra("tahaieh_food",data.get(position).getTahaieh());
                        activity.startActivity(intent);
                    }
                    else{
                        alertDialog();
                    }

                }
            });
        } else {
            grid = view;
        }
        return grid;
    }
    /*4.method to show AlertDialog*/
    public  void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false).setMessage(" خرید درون برنامه ای...")
                .setPositiveButton(
                        "خرید؟ ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                )
                .setNeutralButton("خروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog _alert = builder.create();
        _alert.setTitle(" ");
        _alert.setIcon(R.drawable.ic_cancel);
        _alert.show();
    }
}
