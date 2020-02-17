package com.example.sarashpaz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FrgImage extends Fragment {

    ImageView imageView,imgClose;
    public int pic;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=view.findViewById(R.id.img_frgimg);
        imgClose=view.findViewById(R.id.img_frgclose);
        imageView.setImageResource(pic);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        pic= getArguments().getInt("image_img");
        Log.i("bundle:)))))))", String.valueOf(pic));
//
        if(bundle== null){
            Log.i("bundle","null:)))))))))");
        }
        else{
            Log.i("bundle","okkk:)))))))))");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_img, container, false);

    }
    public static FrgImage newInstance(int img) {
        FrgImage fragment = new FrgImage();
        Bundle args = new Bundle();
        args.putInt("image_img", img);
        fragment.setArguments(args);
        return fragment;

    }
}
