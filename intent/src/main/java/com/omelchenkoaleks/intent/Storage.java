package com.omelchenkoaleks.intent;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    private static final String PREFERENCES_1 = "PREFERENCES_1";
    private static final String PREFERENCES_2 = "PREFERENCES_2";
    private static final String KEY_STATION_1 = "KEY_STATION_1";
    private static final String KEY_STATION_2 = "KEY_STATION_2";

    private Context mContext;
    private SharedPreferences mSharedPreferences_1;
    private SharedPreferences mSharedPreferences_2;

    public Storage(Context context) {
        mContext = context;
        mSharedPreferences_1 = mContext.getSharedPreferences(PREFERENCES_1, Context.MODE_PRIVATE);
        mSharedPreferences_2 = mContext.getSharedPreferences(PREFERENCES_2, Context.MODE_PRIVATE);
    }


    String getStation() {
        String station;

        station = mSharedPreferences_1.getString(KEY_STATION_1, null);

        if  (station != null) {
            station = mSharedPreferences_2.getString(KEY_STATION_2, null);
        }

        if (station == null) {
            station = mContext.getResources().getString(R.string.no_station_text);
        }
        return station;
    }

    void storeStation(String stationName) {
        SharedPreferences.Editor editor_1 = mSharedPreferences_1.edit();
        if (stationName != null && stationName.equals(editor_1)) {
            editor_1.putString(KEY_STATION_1, stationName);
        } else {
            editor_1.remove(KEY_STATION_1);
        }
        editor_1.apply();

        SharedPreferences.Editor editor_2 = mSharedPreferences_2.edit();
        if (stationName != null && stationName.equals(editor_2)) {
            editor_2.putString(KEY_STATION_2, stationName);
        } else {
            editor_2.remove(KEY_STATION_2);
        }
        editor_2.apply();
    }
}
