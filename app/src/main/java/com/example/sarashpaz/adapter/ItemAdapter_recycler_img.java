package com.example.sarashpaz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarashpaz.FrgImage;
import com.example.sarashpaz.model.ItemRecycler;
import com.example.sarashpaz.R;

import java.util.List;

public class ItemAdapter_recycler_img extends RecyclerView.Adapter<ItemAdapter_recycler_img.MyViewHolder3> {

    List<ItemRecycler> item3ListRecyclerimg;
    Context mContext3;
    public static boolean flag_img = false;

    public ItemAdapter_recycler_img(List<ItemRecycler> item3ListRecyclerimg, Context mContext3) {
        this.item3ListRecyclerimg = item3ListRecyclerimg;
        this.mContext3 = mContext3;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_img, parent, false);
        return new MyViewHolder3(aView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, final int position) {
//        Item_recycler_img aItemRecyclerimg = item3ListRecyclerimg.get(position);
//        holder.aAvatar3.setImageResource(aItemRecyclerimg.getuAvatar3());

        holder.aAvatar3.setImageResource(item3ListRecyclerimg.get(position).getuAvatar());
        holder.aAvatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("bundle:)))))))", String.valueOf(item3ListRecyclerimg.get(position).getuAvatar()));

//                flag_img = true;
                FrgImage frg_img = new FrgImage();
//                frg_img = FrgImage.newInstance(item3ListRecyclerimg.get(position).getuAvatar3());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new FrgImage();
                myFragment = frg_img.newInstance(item3ListRecyclerimg.get(position).getuAvatar());
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_img, myFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return item3ListRecyclerimg.size();
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {

        public ImageView aAvatar3;

        public MyViewHolder3(View itemView) {
            super(itemView);
            aAvatar3 = itemView.findViewById(R.id.img_recycler_img);

        }
    }

}