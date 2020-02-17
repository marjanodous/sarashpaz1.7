package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.sarashpaz.model.SharedPreferencesManager;
import com.example.sarashpaz.model.userApp;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivitySplash extends AppCompatActivity {

    /*4.controls SharedPreferencesManager class*/
    userApp user = new userApp();
    public String StoredValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*2.to get value Exit from method returnExit */
       StoredValue = returnExit();

       /*3.open login or main with if and else Control Statement*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /*4.get value to check first run or no*/
                final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(ActivitySplash.this);
                user = sharedPreferencesManager.get_shared_preferences();

                if (user.getFirst_time_run() == true || StoredValue =="exit") {
                    Intent i = new Intent(ActivitySplash.this, ActivityLogin.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(ActivitySplash.this, ActivityMain.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /*1.return value exit for Sign out of account */
    public String returnExit(){
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("Exit", MODE_PRIVATE);
        String val = myPrefs.getString("exit", null);
        return val;
    }
}
