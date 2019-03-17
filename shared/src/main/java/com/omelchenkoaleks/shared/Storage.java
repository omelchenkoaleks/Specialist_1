package com.omelchenkoaleks.shared;

import android.content.Context;
import android.content.SharedPreferences;

class Storage {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String KEY_STATION_SHARED = "KEY_STATION_SHARED";

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    Storage(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    String getStation() {
        String station = mSharedPreferences.getString(KEY_STATION_SHARED, null);
        if (station == null) {
            station = mContext.getResources()
                    .getString(R.string.no_text);
        }
        return station;
    }

    void storeStation(String stationName) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (stationName != null) {
            editor.putString(KEY_STATION_SHARED, stationName);
        } else {
            editor.remove(KEY_STATION_SHARED);
        }
        editor.apply();
    }
}
