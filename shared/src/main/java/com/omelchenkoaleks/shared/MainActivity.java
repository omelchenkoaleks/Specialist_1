package com.omelchenkoaleks.shared;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int CODE_STATION_LIST = 1000;
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String KEY_STATION_SHARED = "KEY_STATION_SHARED";

    private SharedPreferences mSharedPreferences;
    private String mSelectedStation;
    private TextView mNameStationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameStationText = findViewById(R.id.station_text);

        mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        mSelectedStation = mSharedPreferences.getString(KEY_STATION_SHARED, null);
        if (mSelectedStation != null) {
            mNameStationText.setText(mSelectedStation);
        } else {
            mNameStationText.setText(getString(R.string.no_text));
        }
    }

    public void chooseStation(View view) {
        Intent intent = new Intent(this, ListStationMetroActivity.class);
        startActivityForResult(intent, CODE_STATION_LIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_STATION_LIST) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            if (resultCode == RESULT_OK && data != null) {
                String selectedStation = data
                        .getStringExtra(ListStationMetroActivity.EXTRA_RESULT_STATION_TEXT_VIEW);
                mNameStationText.setText(selectedStation);
                editor.putString(KEY_STATION_SHARED, selectedStation);
            } else {
                mNameStationText.setText(getString(R.string.no_text));
                editor.remove(KEY_STATION_SHARED);
            }
            editor.apply();
        }
    }
}
