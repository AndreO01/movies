package com.teste.movies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

public class AppPreferences {

    private static final String PREFERENCES_FILE = "preferences";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_DATA = "user_date";
    private static final String FAVORITES = "favorites";

    private SharedPreferences sharedPrefs;
    private static AppPreferences ourInstance;

    public static AppPreferences getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppPreferences(context);
        }

        return ourInstance;
    }

    private AppPreferences(Context context) {
        sharedPrefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public String getUserEmail() {
        return sharedPrefs.getString(USER_EMAIL, "");
    }

    public void setUserEmail(String email) {
        sharedPrefs.edit().putString(USER_EMAIL, email).apply();
    }

    public void clearUserData() {

        sharedPrefs.edit().clear().apply();
    }

    public JSONObject getUserData() {

        try {
            return new JSONObject(sharedPrefs.getString(USER_DATA, ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    public void setUserData(String userData) {
        sharedPrefs.edit().putString(USER_DATA, userData).apply();
    }

    public String getFavorites() {
        return sharedPrefs.getString(FAVORITES, "");
    }

    public void setFavorites(String favorites) {
        sharedPrefs.edit().putString(FAVORITES, favorites).apply();
    }

}
