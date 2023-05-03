package com.teams.vidhividhan.data.prefrences;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String PREF_NAME = "user_preference";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String KEY_FIRST_VISIT = "first_visit";
    private static final String IS_LOGIN = "is_login";

    private static final String IS_REFRESH = "is_refresh";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String USER_PHONE_NO = "userPhnNumber";

    private static final String USER_EMAIL = "userEmail";
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearPreference() {
        pref.edit().clear().apply();
    }

    public Boolean getFirstVisit() {
        return pref.getBoolean(KEY_FIRST_VISIT, true);
    }

    public void setFirstVisit(Boolean data) {
        editor.putBoolean(KEY_FIRST_VISIT, data);
        editor.commit();
    }


    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, "");
    }

    public void setAccessToken(String data) {
        editor.putString(ACCESS_TOKEN, data);
        editor.commit();
    }

    public String getRefreshToken() {
        return pref.getString(REFRESH_TOKEN, "");
    }

    public void setRefreshToken(String data) {
        editor.putString(REFRESH_TOKEN, data);
        editor.commit();
    }

    public Boolean getIsRefresh() {
        return pref.getBoolean(IS_REFRESH, false);
    }
    public void setIsRefresh(Boolean data) {
        editor.putBoolean(IS_REFRESH, data);
        editor.commit();
    }


    public Boolean getIsLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(Boolean data) {
        editor.putBoolean(IS_LOGIN, data);
        editor.commit();
    }

    public String getUserPhoneNo() {
        return pref.getString(USER_PHONE_NO, "");
    }

    public void setUserPhoneNo(String data) {
        editor.putString(USER_PHONE_NO, data);
        editor.commit();
    }

    public String getUserEmail() {
        return pref.getString(USER_EMAIL, "");
    }

    public void setUserEmail(String data) {
        editor.putString(USER_EMAIL, data);
        editor.commit();
    }




}