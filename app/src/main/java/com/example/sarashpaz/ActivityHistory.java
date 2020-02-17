package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityHistory extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mWebView = findViewById(R.id.web_view);
        String siteUrl = "https://tabnakjavan.com/fa/news/9638/%DB%B6%DB%B0-%D8%B1%D8%A7%D8%B2-%D8%AF%D8%B1%D8%AE%D8%B4%DB%8C%D8%AF%D9%86-%D8%AF%D8%B1-%D8%AF%D9%86%DB%8C%D8%A7%DB%8C-%D8%A2%D8%B4%D9%BE%D8%B2%DB%8C";
        mWebView.loadUrl(siteUrl);
    }
    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
