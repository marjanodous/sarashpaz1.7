package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityDastebandi extends AppCompatActivity {

    /*create object for xml*/
    private ImageView imgBack, imgTanagholat, imgSonati, imgModern, imgSoap, imgRejimi,
            imgDaneshjuee, imgTazeenat, imgnoshidani, imgTorshi, imgdeser, imgmahali, imgsalad, imgcake, imgkoodak;
    private TextView txtsoup, txtmodern, txtsonaty, txtdaneshjuee,
            txtrejimi, txttanaqolat;
    public static boolean tanagholat, sonati, modern, soap, rejimi, daneshjuee = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dastebandi);

        /* Get object from xml file*/
        txtsoup = findViewById(R.id.txtsoup);
        txtmodern = findViewById(R.id.txtmodern);
        txtsonaty = findViewById(R.id.txtsonati);
        txtdaneshjuee = findViewById(R.id.txtdaneshjuee);
        txtrejimi = findViewById(R.id.txtrejimi);
        txttanaqolat = findViewById(R.id.txttanaqolat);
        imgSoap = findViewById(R.id.img_soup);
        imgModern = findViewById(R.id.img_modern);
        imgSonati = findViewById(R.id.img_sonati);
        imgTanagholat = findViewById(R.id.img_tazeenat);
        imgRejimi = findViewById(R.id.img_rejimi);
        imgDaneshjuee = findViewById(R.id.img_daneshjuee);
        imgTazeenat = findViewById(R.id.img_tanagholat);
        imgnoshidani = findViewById(R.id.img_nushidani);
        imgTorshi = findViewById(R.id.img_torshi);
        imgdeser = findViewById(R.id.img_deser);
        imgsalad = findViewById(R.id.img_salad);
        imgcake = findViewById(R.id.img_cake);
        imgkoodak = findViewById(R.id.img_koodak);
        imgmahali = findViewById(R.id.img_mahali);
        imgBack = findViewById(R.id.video_backhome);


        /*1.set 6 item imagview click listener to show Activity Menu*/
        imgSoap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soap = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("soap", soap);
                startActivity(intent);
                ActivityMenu.titleMenu = txtsoup.getText().toString();
            }
        });
        imgModern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modern = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("modern", modern);
                startActivity(intent);
                ActivityMenu.titleMenu = txtmodern.getText().toString();
            }
        });
        imgSonati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonati = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("sonati", sonati);
                startActivity(intent);
                ActivityMenu.titleMenu = txtsonaty.getText().toString();
            }
        });
        imgTanagholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanagholat = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("tanagholat", tanagholat);
                startActivity(intent);
                ActivityMenu.titleMenu = txttanaqolat.getText().toString();
            }
        });
        imgRejimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rejimi = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("rejimi", rejimi);
                startActivity(intent);
                ActivityMenu.titleMenu = txtrejimi.getText().toString();
            }
        });
        imgDaneshjuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daneshjuee = true;
                Intent intent = new Intent(ActivityDastebandi.this, ActivityMenu.class);
                intent.putExtra("daneshjuee", daneshjuee);
                startActivity(intent);
                ActivityMenu.titleMenu = txtdaneshjuee.getText().toString();
            }
        });

        /*2.click images to show Alert dialog*/
        imgTazeenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgnoshidani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgTorshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgdeser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgmahali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgsalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgkoodak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });


        /*3.click back to home*/
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*3.click to back home , this activity is finish*/
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /*4.method to show AlertDialog*/
    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDastebandi.this);
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
