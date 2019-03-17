package com.omelchenkoaleks.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int CODE_STATION_LIST = 1000;

    private TextView mNameStationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameStationText = findViewById(R.id.station_text);
    }

    public void chooseStation(View view) {
        Intent intent = new Intent(this, ListStationMetroActivity.class);
        startActivityForResult(intent, CODE_STATION_LIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_STATION_LIST) {
            if (resultCode == RESULT_OK && data != null) {
                String selectedStation = data
                        .getStringExtra(ListStationMetroActivity.EXTRA_RESULT_STATION_TEXT_VIEW);
                mNameStationText.setText(selectedStation);
            } else {
                Toast.makeText(this, "NO ...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
