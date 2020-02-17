package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.AdapterSearch;
import com.example.sarashpaz.model.ItemRecycler;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivitySearch extends AppCompatActivity {

    /*create object for xml*/
    ListView listViewSearch;
    ImageView imgClear;
    EditText edtNameSearch;
    TextView txtResult;

    public ArrayList<ItemRecycler> foodsArrayListDefault, foodsArrayListSearch;
    AdapterSearch adapterDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /* Get object from xml file*/
        listViewSearch = findViewById(R.id.listView_search);
        imgClear = findViewById(R.id.imgsearch_clear);
        edtNameSearch = findViewById(R.id.edt_name_search);
        txtResult = findViewById(R.id.result_search);

        /*2. calling method defult to listdefault*/
        setdefualt();

        /*4. clear text user typing*/
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNameSearch.setText("");
            }
        });

        /*5.listeners for checked user typing*/
        edtNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                /*9.when text the length > 3 char*/
                String text = edtNameSearch.getText().toString().trim();
                if (text.length() >= 2) {
                    /* calling method set item list equal with user typing*/
                    serchArray(text);
                }
            }
            /*6.when user clear text list montakhb showing*/
            @Override
            public void afterTextChanged(Editable editable) {
                String text = edtNameSearch.getText().toString().trim();
                if (text.length()<=0) {
                    txtResult.setText("نتیجه جستجو");
                    foodsArrayListSearch = new ArrayList<>();
                    setItemListSearch();
                }
            }
        });

    }

    /* 8.method from checked name is equal whit list item defult*/
    public void serchArray(String name) {
        foodsArrayListSearch = new ArrayList<>();
        String sname = name;
        boolean flag=false;
        for (ItemRecycler item : foodsArrayListDefault) {
            int img =item.getuAvatar();
            String strlist = item.getuName();
            String mavad = item.getMavad();
            String tahaieh = item.getTahaieh();
            if (strlist.contains(sname)) {
                flag=true;
                /*add equal item to list ActivitySearch*/
                foodsArrayListSearch.add(new ItemRecycler(img,strlist,mavad,tahaieh));
                /*calling method to show list ActivitySearch*/
                setItemListSearch();
                txtResult.setText("نتیجه جستجو");
            } else if(flag==false){
                /*set empty item to listsearch*/
                foodsArrayListSearch = new ArrayList<>();
                setItemListSearch();
                txtResult.setText("موردی یافت نشد");
            }

        }

    }
    /*7.method show item to list*/
    public void setItemListSearch() {
        adapterDefault = new AdapterSearch(ActivitySearch.this, R.layout.item_search, foodsArrayListSearch);
        listViewSearch.setAdapter(adapterDefault);
    }

    /*1. method add item to list defult montakhab */
    public void setdefualt() {
        foodsArrayListDefault = new ArrayList<>();
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"قیمه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"باقالی پلو","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"جوجه کباب","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"کباب برگ","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"کباب چنجه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"قرمه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"قلیه ماهی","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"سبزی پلوماهی","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"فسنجون","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"مرغ آلو","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"کباب کوبیده","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListDefault.add(new ItemRecycler(R.drawable.img1,"زرشک پلو","مواد لازم سرچ","طرز تهیه سرچ"));
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
