package com.omelchenkoaleks.shared;

import android.content.Context;
import android.content.SharedPreferences;

class Storage {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String KEY_STATION_SHARED = "KEY_STATION_SHARED";

    private SharedPreferences mSharedPreferences;
    private String mDefaultString;

    Storage(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        mDefaultString = context.getResources()
                .getString(R.string.no_text);
    }

    String getStation() {
        String station = mSharedPreferences.getString(KEY_STATION_SHARED, null);
        return (station == null) ? mDefaultString : station;
    }

    void setStation(String stationName) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (stationName != null) {
            editor.putString(KEY_STATION_SHARED, stationName);
        } else {
            editor.remove(KEY_STATION_SHARED);
        }
        editor.apply();
    }
}
