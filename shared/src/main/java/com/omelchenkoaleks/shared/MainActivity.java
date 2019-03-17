package com.omelchenkoaleks.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int CODE_STATION_LIST = 1000;

    private Storage mStorage;
    private TextView mNameStationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameStationText = findViewById(R.id.station_text);
        mStorage = new Storage(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNameStationText.setText(mStorage.getStation());
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
                mStorage.setStation(selectedStation);
            } else {
                mStorage.setStation(null);
            }
            mNameStationText.setText(mStorage.getStation());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_clear:
                mStorage.setStation(null);
                mNameStationText.setText(mStorage.getStation());
                return true;
            case R.id.item_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
