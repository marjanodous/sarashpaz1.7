package com.example.sarashpaz.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sarashpaz.model.userApp;

public class SharedPreferencesManager {
    private static SharedPreferences sharedPreferences;
    private static final String SHARED_PREFERENCES_NAME = "user_shared_preferences";

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
    }

    public userApp get_shared_preferences() {
        userApp user = new userApp();
        user.setFirst_time_run(sharedPreferences.getBoolean("first_time_run", true));
        return user;
    }

    public void set_false_first_time(userApp user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first_time_run", user.getFirst_time_run());
        editor.apply();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
