package com.example.sarashpaz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarashpaz.model.ItemRecycler;
import com.example.sarashpaz.R;
import com.example.sarashpaz.ActivityTozihat;

import java.util.List;

public class ItemAdapter_recycler_myanvade extends RecyclerView.Adapter<ItemAdapter_recycler_myanvade.MyViewHolder> {
    List<ItemRecycler> itemList;
    Context mContext;
    public static boolean flag_myanvade=false;
    public ItemAdapter_recycler_myanvade(List<ItemRecycler> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myanvade, parent, false);
        return new MyViewHolder(aView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.aName.setText(itemList.get(position).getuName());
        holder.aAvatar.setImageResource(itemList.get(position).getuAvatar());
        holder.aAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_myanvade=true;
                Intent intent  = new Intent(mContext, ActivityTozihat.class);
                intent.putExtra("title_myanvade",itemList.get(position).getuName());
                intent.putExtra("image_myanvade",itemList.get(position).getuAvatar());
                intent.putExtra("mavad_myanvade",itemList.get(position).getMavad());
                intent.putExtra("tahaieh_myanvade",itemList.get(position).getTahaieh());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView aAvatar;
        public TextView aName;

        public MyViewHolder(View itemView) {
            super(itemView);
            aAvatar = itemView.findViewById(R.id.img_recycler_myanvade);
            aName = itemView.findViewById(R.id.txt_name_mianvade);

        }
    }
}

