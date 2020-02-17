package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityMenuVideo extends AppCompatActivity {

    ImageView  imageView;
    VideoView videoView1, videoView2, videoView3;
    Boolean flag_play = false;
    Boolean flag_play1 = false;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuvideo);
        videoView1 = findViewById(R.id.video1);
        videoView2 = findViewById(R.id.video2);
        videoView3 = findViewById(R.id.video3);
        scrollView = findViewById(R.id.scrollView_video);
        imageView = findViewById(R.id.video_backhome);

        videoView1.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video1);
        final MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView1);
        videoView1.setMediaController(controller);
//        videoView1.start();
        videoView1.requestFocus();
        videoView1.setKeepScreenOn(true);
        videoView1.seekTo(1);


        videoView2.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video2);
        final MediaController controller2 = new MediaController(this);
        controller2.setAnchorView(videoView2);
        videoView2.setMediaController(controller2);
//        videoView2.start();
        videoView2.requestFocus();
        videoView2.setKeepScreenOn(true);
        videoView2.seekTo(200);


        videoView3.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video3);
        final MediaController controller3 = new MediaController(this);
        controller3.setAnchorView(videoView3);
        videoView3.setMediaController(controller3);
//        videoView3.start();
        videoView3.requestFocus();
        videoView3.setKeepScreenOn(true);
        videoView3.seekTo(1);

        videoView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (videoView2.isPlaying()||videoView3.isPlaying()) {
                    videoView1.setMediaController(null);

                }
                else{
                    videoView1.setMediaController(controller);

                }
                return false;
            }
        });
        videoView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (videoView1.isPlaying()||videoView3.isPlaying()) {
                    videoView2.setMediaController(null);
                }
                else{
                    videoView2.setMediaController(controller2);

                }
                return false;
            }
        });
        videoView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (videoView1.isPlaying()||videoView2.isPlaying()) {
                    videoView3.setMediaController(null);

                }
                else{
                    videoView3.setMediaController(controller3);

                }
                return false;
            }
        });
//        controller.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(ActivityMenuVideo.this, "controller touch", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (videoView1.isPlaying()) {
                    videoView2.setMediaController(null);
                    videoView3.setMediaController(null);
                }
                if (videoView2.isPlaying()) {
                    videoView1.setMediaController(null);
                    videoView3.setMediaController(null);

                }
                if (videoView3.isPlaying()) {
                    videoView1.setMediaController(null);
                    videoView2.setMediaController(null);

                }
                else if(!videoView1.isPlaying()&&!videoView2.isPlaying()&&!videoView3.isPlaying()){
                    videoView1.setMediaController(controller);
                    videoView2.setMediaController(controller2);
                    videoView3.setMediaController(controller3);

                }
                controller.hide();
                controller2.hide();
                controller3.hide();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
