package com.example.sarashpaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.ItemAdapter_recycler_behtarin_dastoor_pokht_ha;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_img;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_myanvade;
import com.example.sarashpaz.adapter.SlidingImage_Adapter;
import com.example.sarashpaz.model.ImageModel;
import com.example.sarashpaz.model.ItemRecycler;
import com.example.sarashpaz.model.userApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityMain extends AppCompatActivity {

    /*create object for xml*/
    ImageView imgMenu, imgHomeMenu, imgListMenu;
    DrawerLayout myDraw;
    TextView txthistory, video, userName, txtBackHome, txtFavorit, txtabout, txtcall_me, txtExit;
    BottomNavigationView bottomNav;
    MediaPlayer music;
    ImageView btn_play;

    Boolean flag_play = false;
    public static boolean flag_Dasteh, flag_Search = false;

    //for image slider
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};

    //for recycler myanvade
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    List<ItemRecycler> item1 = new ArrayList<>();
    ItemAdapter_recycler_myanvade mAdapter;

    //for recycler behtarin_dastoor_pokht_ha
    List<ItemRecycler> item2 = new ArrayList<>();
    ItemAdapter_recycler_behtarin_dastoor_pokht_ha mAdapter2;

    //for recycler img
    List<ItemRecycler> item3 = new ArrayList<>();
    ItemAdapter_recycler_img mAdapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Get object from xml file*/
        imgMenu = findViewById(R.id.img_menu);
        myDraw = findViewById(R.id.myDraw);
        userName = findViewById(R.id.username);
        txtBackHome = findViewById(R.id.txt_home);
        imgHomeMenu = findViewById(R.id.img_home_menu);
        txthistory = findViewById(R.id.txt_history);
        imgListMenu = findViewById(R.id.img_history);
        txtFavorit = findViewById(R.id.txt_favorite);
        video = findViewById(R.id.text_video);
        txtabout = findViewById(R.id.text_about);
        txtcall_me = findViewById(R.id.txt_call_me);
        txtExit = findViewById(R.id.txt_exit);




        /*for music*/
        music = MediaPlayer.create(ActivityMain.this, R.raw.music);
        music.start();
        music.setLooping(true);
        btn_play = findViewById(R.id.img_music);
        btn_play.setImageResource(R.drawable.ic_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag_play) {
                    btn_play.setImageResource(R.drawable.ic_pause);
                    music.pause();
                    flag_play = true;
                } else {
                    btn_play.setImageResource(R.drawable.ic_play);
                    music.start();
                    flag_play = false;

                }
            }
        });
        /*end music*/

        //==============open draw=============================
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.openDrawer(Gravity.RIGHT);
            }
        });

        //for image slider
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();

        //for recycler myanvade
        recyclerView = findViewById(R.id.recycler_view_myanvade);
        mAdapter = new ItemAdapter_recycler_myanvade(item1, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = findViewById(R.id.recycler_view_myanvade);
        myList.setLayoutManager(layoutManager);
        setDeta();
        recyclerView.setNestedScrollingEnabled(true);

        //for recycler behtarin_dastoor_pokht_ha
        recyclerView2 = findViewById(R.id.recycler_view_bartarinDastoorPokht);
        mAdapter2 = new ItemAdapter_recycler_behtarin_dastoor_pokht_ha(item2, this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setAdapter(mAdapter2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList2 = findViewById(R.id.recycler_view_bartarinDastoorPokht);
        myList2.setLayoutManager(layoutManager2);
        setDeta2();
        recyclerView2.setNestedScrollingEnabled(true);

        //for recycler img
        recyclerView3 = findViewById(R.id.recycler_view_img);
        mAdapter3 = new ItemAdapter_recycler_img(item3, this);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext());
        recyclerView3.setLayoutManager(mLayoutManager3);
        recyclerView3.setAdapter(mAdapter3);
        setDeta3();
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList3 = findViewById(R.id.recycler_view_img);
        myList3.setLayoutManager(layoutManager3);

        //for bottom navigation
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.item_home);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_dastebandi:
                        startActivity(new Intent(ActivityMain.this, ActivityDastebandi.class));
                        flag_Dasteh = true;
                        return true;
                    case R.id.item_home:
                        return true;
                    case R.id.item_search:
                        startActivity(new Intent(ActivityMain.this, ActivitySearch.class));
                        flag_Search = true;
                        return true;
                }
                return false;
            }
        });

        /*2.set to show UserName*/
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("TITLE", MODE_PRIVATE);
        String StoredValue = myPrefs.getString("title", null);
        userName.setText(StoredValue);

        /*3.click  to back home text*/
        txtBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.closeDrawers();
            }
        });

        /*3.click  to back home img*/
        imgHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.closeDrawers();
            }
        });

        /*4.txt for go to ActivityHistory*/
        txthistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityHistory.class));
            }
        });

        /*5.img for go to ActivityHistory*/
        imgListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityDastebandi.class));
            }
        });

        /* 6.txt go to favorit*/
        txtFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityLiked.class));
            }
        });

        /*7.for go to video ActivityMenuVideo*/
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityMenuVideo.class));
            }
        });

        /*8.for goto about*/
        txtabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityAbout.class));
            }
        });

        /*9.for go to callme*/
        txtcall_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityCallme.class));
            }
        });

        /* 10.for save Exit to SharedPreferences from user */
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exit = "exit";
                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("Exit", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.putString("exit", exit);
                edit.apply();
                finish();
            }
        });

    }

    //for image slider
    private ArrayList<ImageModel> populateList() {
        ArrayList<ImageModel> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void init() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(ActivityMain.this, imageModelArrayList));
        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);
        NUM_PAGES = imageModelArrayList.size();
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }

    /*1.for set array value myanvade*/
    private void setDeta() {
        item1.add(new ItemRecycler(R.drawable.myan1, "شکلات دانه ای غنی از پروتئین",
                "۱۲ خرمای مجول\n" +
                        "۴/۱ فنجان دانه شاهدانه\n" +
                        "۴/۱ فنجان دانه چیا\n" +
                        "۴/۱ فنجان دانه کنجد\n" +
                        "۴/۱ فنجان پودر کاکائو\n" +
                        "½ عصاره وانیل\n" +
                        "۴/۱ قاشق چای خوری دارچین\n" +
                        "۴/۱ قاشق نمک دریا به منظور طعم دهی\n" +
                        "۴/۱ فنجان کاکائو\n",
                "۱٫ خرما را در آسیاب قرار دهید و تا زمان خمیر مانند شدن ادامه دهید.\n" +
                        "۲٫ شاهدانه، چیا، کنجد، کاکائو، وانیل، دارچین و نمک را بی افزایید. تا زمان ترکیب کامل هم بزنید. کاکائو را نیز ترکیب کنید. خمیر باید چسبنده باشد (درصورتی که چسبنده نباشد، یک یا دو قاشق چای خوری آب بی افزایید).\n" +
                        "۳٫ آن ها را به صورت توپ های کوچک درآورده و به مدت ۲۰ دقیقه در فریزر قرار دهید. باقیمانده را در فریزر نگهداری نمایید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan2, "ترکیبی از آجیل و میوه جات خشک شده خانگی",
                "½ فنجان کران بری خشک شده\n" +
                        "½ فنجان بادام رنده شده\n" +
                        "½ فنجان دانه کدوتنبل\n" +
                        "½ فنجان گردو\n" +
                        "½ فنجان کشمش\n",
                "تمام موارد را ترکیب کرده و به ۶ قسمت تقسیم نمایید.\n" +
                        "\nتهیه این نوع از اسنک های سریع نیز بسیار آسان می باشد. شما می توانید این ترکیب را در بسیار از فروشگاه ها مشاهده کنید بنابراین دیگر بهانه ای برای استفاده از میان وعده های ناسالم وجود ندارد. اما من ترجیح می دهم که خودم آن را ترکیب کنم زیرا در این صورت تمام موارد موردعلاقه را ترکیب می کنم و نگران افزودنی های غیرمجاز نیستم.\n" +
                        "ازآنجایی که آجیل، دانه ها، نارگیل و توت های خشک شده همگی گزینه های بسیار عالی هستند بنابراین از موادی نظیر چوب شور و غلات یا حبوبات فراوری شده پرهیز نمایید.\n" +
                        "نوع موردعلاقه من ترکیب کرانبری و کدوتنبل می\u200Cباشد. دستورالعمل اصلی بیان دارد که ½ پیمانه از هرکدام را ترکیب کرده و در ۴ وعده مصرف نماییم اما این مقدار دارای کالری بسیار بالایی می باشد. بنابراین من آن را در ۶ وعده استفاده می کنم و درنتیجه ۶ گرم پروتئین و ۱۶۳ کالری انرژی را برای بدن خود فراهم می آورم و یک بار تهیه کردن نیز تا آخر هفته کافی می باشد.\n"));
        item1.add(new ItemRecycler(R.drawable.myan3, "لقمه بوقلمون، آووکادو و حمص",
                "یک یا دوتکه گوشت بوقلمون\n" +
                        "۲ ورقه آووکادو\n" +
                        "یک قاشق حمص\n",
                "گوشت بوقلمون را پهن کنید. اگر از دو ورقه استفاده می کنید آنها را روی هم بگذارید.\n" +
                        "حمص را بر روی گوشت قرار دهید. آووکادو را افزوده و آن را به حالت لقمه دربیاورید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan4, "اسموتی سبز غنی از پروتئین",
                "یک فنجان شیر نارگیل بدون شکر\n" +
                        "۲ فنجان اسفناج تازه\n" +
                        "یک موز یخ زده\n" +
                        "۲ قاشق روغن بادام\n" +
                        "۲ قاشق چای خوری عصاره وانیل\n" +
                        "۴/۱ فنجان (۱ پیمانه) پودر پروتئین وی\n" +
                        "یک فنجان یخ\n",
                "تمام مواد را ترکیب کرده و تا زمان رقیق شدن هم بزنید.\n" ));
        item1.add(new ItemRecycler(R.drawable.myan5, "نخود سرخ کرده",
                "۱ قاشق چای خوری روغن زیتون\n" +
                        "۱٫۵ قاشق چای خوری پودر فلفل\n" +
                        "۱٫۵ قاشق چای خوری زیره\n" +
                        "۴/۱ قاشق چای خوری نمک\n" +
                        "۸/۱ قاشق چای خوری فلفل کاین\n" +
                        "۲ قوطی نخود\n",
                "۱٫ فر را در دمای ۴۰۰ فارنهایت قرار دهید. تمام مواد را در یک کاسه بزرگ مخلوط کنید.\n" +
                        "۲٫ نخودها را در ماهی تابه قرار داده و در حدود ۳۵ تا ۴۰ دقیقه بپزید. آن را گرم سرو کنید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan6,
                "دسر ماست یونانی",
                "۶ اونس ماست یونانی\n" +
                        "۳/۱ فنجان جوی بدون گلوتن، به صورت خام\n" +
                        "۱ قاشق چای خوری دانه چیا\n" +
                        "۲ قاشق شیر دلخواه (من شیر بادام را ترجیح می دهم)\n" +
                        "۱ فنجان میوه و توت \n",
                "۱٫ ماست، جو، دانه چیا، و شیر را در یک کاسه ترکیب کنید. آن را در یک ظرف شیشه ای پهن کنید.\n" +
                        "۲٫ نیمی از میوه ها و توت ها را بر روی آن ریخته و سپس باقی مخلوط را بر روی آن بریزید.\n" +
                        "۳٫ شب آن را در یخچال قرار داده و یا بعد از تهیه مصرف نمایید.\n"));
        mAdapter.notifyDataSetChanged();
    }

    /*1.for set array value behtarin_dastoor_pokht_ha*/
    private void setDeta2() {
        item2.add(new ItemRecycler(R.drawable.kabab, "برای کباب کوبیده زعفرانی",
                "گوشت چرخ کرده :یک کیلو\n" +
                        "پیاز متوسط :4 عدد\n" +
                        "نمک و فلفل:به مقدار لازم\n" +
                        "زعفران :به مقدار لازم",
                "در تهیه این کباب، از گوشت چرخ کرده ای که 30 در صد چربی داشته باشد استفاده کنید. گوشت را در یک ظرف مسی بریزید و پیاز را داخل آن رنده کنید.\n" +
                        "نمک، فلفل و زعفران را اضافه کنید و تا جایی که می توانید، گوشت را ورز دهید تا حدی که گوشت، حالت چسبندگی پیدا کند. می توان برای ایجاد چسبدگی بیشتر، از زرده یک تخم مرغ استفاده کرد.\n" +
                        "برای سیخ کردن کباب هم باید توجه داشته باشید که از سیخ های پهن برای پخت این کباب باید استفاده کنید.\n" +
                        "قبل از اینکه شروع به کار کنید، مقدماتی لازم است. ابتدا در کاسه کوچکی مقداری آب سرد بریزید و کنار دست تان قرار دهید. حالا پیش از برداشتن مایه کباب دست تان را در آب خیس کرده، یک مشت از مایه را بردارید و به شکل دوک درآورید؛ سپس همان طور که مواد در دست راست تان قرار دارد، سیخ را روی مایه دوکی بگذارید و شروع به پوشاندن سطح سیخ با مایه کباب کنید.\n" +
                        "در پایان دوباره دست تان را در آب سرد فرو کنید و روی سیخ فشار دهید تا اثر انگشتان شما بر گوشت باقی بماند. حالا ابتدا و انتهای کباب را به سیخ بچسبانید و کناری بگذارید. همین کار را درباره همه مواد و سیخ ها انجام دهید."));
        item2.add(new ItemRecycler(R.drawable.pitzza1,
                "پیتزا پپرونی",
                "گوشت چرخ کرده:200گرم\n" +
                        "فلفل دلمه ای:1عدد\n" +
                        "رب گوجه فرنگی :3قاشق غذاخوری\n" +
                        "قارچ :200گرم\n" +
                        "پیاز :1عدد\n" +
                        "کالباس : 200گرم\n" +
                        "پنیرپیتزا :به مقدازلازم\n" +
                        "نمک فلفل پودر اویشن:به مقذار دلخواه\n",
                "  پیاز راخرد کنید ودرون ماهیتابه بامقداری روغن تفت بدهید تانرم شود سپس گوشت نمک فلفل وپودر اویشن رابه ان اضافه کرده وباحرارت ملایم تفت میدهیم!پس از اینکه رنگ گوشت تغییر کرد 1قاشق رب گوجه فرنگی اضافه نموده ودوباره تفت میدهیم وپس از 5دقیقه از روی حرارت اجاق برمیداریم!\n" +
                        "        کالباس رامکعبی خرد کنید پیاز راحلقه ای خرد کنید فلفل دلمه ای را ریز کرده مقداری زیتون هم ریز کنید!\n" +
                        "        خمیر پیتزا را نازک پهن کنید 2قاشق رب گوجه فرنگی راتفت داده تا بوی خامی ندهد سپس با قلمو روی خمیر پیتزا پخش کنید ومقداری پنیر بریزید وتمامی موادی راکه خرد کرده بودید(کالباس پیاز و...)به همراه مایه گوشتی روی خمیر بچینید ودر انتها با لایه کاملی از پنیر پیتزا پوشش دهیدوروی ان زیتون بریزیدو اجازه دهید پیتزای شما 20دقیقه بماند تا خمیر استراحت کند ودر زمان پخت خوب ترد وبرشته شود.\n" +
                        "        پیتزا را به مدت 15الی 20 دقیقه در طبقه وسط فر میگذاریم البته فر را باید از قبل در دمای 180 درجه گرم کنید!\n" +
                        "        در صورتیکه میخواهید روی پیتزای شما طلایی شود گریل بالا را روشن کنید ومواظب باشد پنیر پیتزایتان نسوزد! پیتزایتان را باسس دلخواه سرو نمایید\n"));
        item2.add(new ItemRecycler(R.drawable.soupjo,
                "سوپ جو قرمز",
                "جو پرک :3/4 لیوان\n" +
                        "آب مرغ :   7 لیوان\n" +
                        "پیاز :1 عدد متوسط\n" +
                        "هویج :2 عدد متوسط\n" +
                        "آبلیمو:2 قاشق غذا خوری\n" +
                        "رب گوجه :یک و نیم قاشق غذا خوری\n" +
                        "آرد :2 قاشق غذا خوری سر پر\n" +
                        "آب :1 لیوان\n" +
                        "جعفری خشک :1 قاشق غذا خوری سرپر\n" +
                        "ادویه کاری :نصف قاشق چایخوری\n" +
                        "نمک و فلفل سیاه :به مقدار کافی\n",
                "ابتدا اسکلت مرغ و هویج وزعفران و سیر وپیاز ونمک و برگ بو را با حدود دو و نیم لیتر آب می گذاریم تا بپزه و سپس آنرا صاف می کنیم .. آب مرغ را هم می تونیم در فریزر نگهداری کنیم و برای درست کردن سوپ از آن استفاده کنیم ..\n" +
                        "پیاز وهویج را خیلی ریز خرد می کنیم.\n" +
                        "مواد فوق را به همراه آب مرغ و جوپرک را روی حرارت می گذاریم تا به جوش بیاد و سپس حرارت را ملایم می کنیم ودر قابلمه را می گذاریم تا حدود نیم ساعت بپزه.\n" +
                        "سپس آرد را دریک لیوان آب سرد حل می کنیم و به همراه ادویه وجعفری به سوپ اضافه می کنیم و صبر می کنیم تا دوباره به جوش بیاد و بوی خامی ارد بره.\n" +
                        "در آخر آبلیمو ورب گوجه را به سوپ اضافه می کنیم. و صبر می کنیم تا سوپ جا بیفته …\n"));
        item2.add(new ItemRecycler(R.drawable.rejimipolosabzijat,
                "پلو سبزیجات با لوبیا قرمز",
                "روغن مايع سه قاشق غذاخوري\n" +
                        "برنج 250 گرم\n" +
                        "فلفل دلمه اي سبزو پیاز نگيني خرد شده هر کدام يك عدد\n" +
                        "پودر چيلي(فلفل قرمز تند) يك قاشق چايخوري\n" +
                        "ذرت پخته 150 گرم\n" +
                        "لوبيا قرمز پخته 225 گرم\n" +
                        "گشنيز تازه خرد شده دو قاشق غذاخوري\n" +
                        "زعفران آب كرده نمک و فلفل زردچوبه به ميزان لازم\n",
                "در تابه روغن را گرم سپس پيازها را اضافه كرده كمي كه سبك شد فلفل قرمز و زردچوبه را اضافه مي كنيم. بعد از آن فلفل دلمه را اضافه كرده و لوبيا قرمز را كه قبل پخته ايم را مي ريزيم و ذرت پخته را به آن مي افزاييم. برنج را درون آبي كه از قبل به جوش آورده ايم همراه با نمك ريخته و كمي زنده تر آبكش مي كنيم سپس درون قابلمه لايه لايه از برنج، مواد و گشنيز ريخته و در آخر زعفران آب كرده را مي ريزيم."));
        item2.add(new ItemRecycler(R.drawable.danesgzorat,
                "ذرت مکزیکی",
                "ذرت شیرین 500 گرم\n" +
                        "نمک و فلفل به میزان لازم\n" +
                        "قارچ 250 گرم\n" +
                        "پنیر پیتزا 100 گرم\n" +
                        "آویشن 1 ق چ\n" +
                        "کره 30 گرم\n" +
                        "روغن مایع 3 تا 4 ق س\n" +
                        "سس مایونز 1 و نیم پیمانه\n" +
                        "نمک 1 ق چ\n" +
                        "فلفل سیاه نصف ق چ\n" +
                        "فلفل قرمز 1/2 ق چ\n" +
                        "پودر گلپر 1 ق چ\n" +
                        "ادویه کاری 1/2 ق چ\n",
                "رای درست کردن ذرت مکزیکی میتوانید از ذرت های شیرین تازه و یا از کنسرو ذرت استفاده کنید.قارچ ها را هم خرد میکنیم و در یک قابلمه ای که 2/3 آن را پر از آب کردیم و مقداری آبلیمو ریختیم و روی حرارت به جوش آمد میریزیم و فقط در حدی که یک جوش بخورد داخل قابلمه نگه میداریم و سریع داخل آبکش میریزیم و زیر آب سرد نگه میداریم .\n" +
                        "بعد از این مراحل، کره و روغن مایع را داخل قابلمه ای میریزیم تا داغ شود و بعد ذرت را داخل کره و روغن کمی تفت میدهیم در حدی که طعم دار شوند. بعد قارچ ها ی بلانچ شده را داخل آن میریزیم و در حد هم زدن و مخلوط کردن تفت میدهیم .\n" +
                        "نمک ، فلفل ، و بقیه ادویه ها را هم اضافه میکنیم و هم میزنیم تا مواد روی حرارت گرم شوند .\n"));
        item2.add(new ItemRecycler(R.drawable.tanaqolatmive,
                "میوه خشک",
                "میوه های کوچک مثل انگور، توت فرنگی، گیلاس و گوجه فرنگی مینیاتوری را می توان به صورت درسته خشک کرد. میوه های متوسط را می توان نصف یا حلقه حلقه کرد و میوه های بزرگ یا خیلی سفت مثل انبه، آناناس و نارگیل را باید ورقه ورقه کرد.",
                " اگر دوست دارید پوست میوه ها را بگیرید ، تکه های میوه را 30 ثانیه در آب جوش بریزید و سپس به یک ظرف آب یخ منتقل کنید تا پوست میوه ها راحت جدا شود.\n" +
                        " برای حفظ ظاهر زیبای میوه ها و به حداقل رسیدن تغییر رنگ شان آنها را 10 دقیقه درون آب لیمو و آب قرار دهید، سپس در آبکش بریزید و با یک حوله ی بدون پرز به آرامی خشک کنید.\n" +
                        " فر را از قبل در دمای 54 تا 71 درجه ی سانتی گراد گرم کنید.\n" +
                        " از درجه ی حرارت پایین تر برای میوه های برش خورده ی نازک مانند سیب یا هلو و از دمای بالاتر برای توت فرنگی ها و سایر توت های کامل استفاده کنید.\n" +
                        " روی سینی فر را با کاغذ مومی بپوشانید و تکه های میوه را با حفظ فاصله بچینید.\n" +
                        "یک توری گرد مخصوص پیتزا یا یک لاینر سیلیکونی روی میوه ها برای جلوگیری از جمع شدن (لوله شدن) آنها قرار دهید.\n" +
                        " میوه ها را درون فر بگذارید و هر دو ساعت سینی فر را بچرخانید.\n" +
                        " هنگامی که میوه خشک بافتی مانند چرم پیدا کرد و حدی منعطف بود، آماده بیرون آوردن از فر است.\n" +
                        " بعد از بیرون آوردن میوه های خشک از فر آنها را در ظرف های پلاستیکی یا شیشه ای بریزید و برای 4 تا 5 روز سر ظرفها را باز بگذارید تا رطوبت باقی مانده آنها تبخیر شود. در طی این روزها ظرف را ، تکان دهید تا میوه ها کمی جابه جا شوند.پس از 5 روز، در ظرف را ببندید\n" +
                        " حدود 10 ماه بعداز این میوه های خشک می توانید استفاده کنید.\n"));
        mAdapter2.notifyDataSetChanged();
    }

    /*1.for set value array img*/
    private void setDeta3() {
        item3.add(new ItemRecycler(R.drawable.design7));
        item3.add(new ItemRecycler(R.drawable.design1));
        item3.add(new ItemRecycler(R.drawable.design2));
        item3.add(new ItemRecycler(R.drawable.design3));
        item3.add(new ItemRecycler(R.drawable.design4));
        item3.add(new ItemRecycler(R.drawable.design5));
        item3.add(new ItemRecycler(R.drawable.design6));
        mAdapter3.notifyDataSetChanged();
    }

    /*11.close first drawer menu with back*/
    @Override
    public void onBackPressed() {
        if (myDraw.isDrawerOpen(GravityCompat.START)) {
            myDraw.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.release();
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
