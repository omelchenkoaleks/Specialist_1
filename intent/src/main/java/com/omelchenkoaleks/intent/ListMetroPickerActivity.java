package com.omelchenkoaleks.intent;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ListMetroPickerActivity extends ListActivity
        implements AdapterView.OnItemClickListener {
    public static final String EXTRA_SELECTED_STATION_MANIFEST= "extra selectedStationManifest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // получаем информацию, какое действие может выполнить это Активити
        Intent intent = getIntent();
        String action = intent.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();


        Resources resources = getResources();
        String[] stationsArray = resources.getStringArray(R.array.stations);
        ArrayAdapter<String> stations = new ArrayAdapter<>(
                this, R.layout.list_item, stationsArray);
        setListAdapter(stations);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CharSequence stationSelected = ((TextView) view).getText();
        Intent result = new Intent();
        result.putExtra(EXTRA_SELECTED_STATION_MANIFEST, stationSelected);
        setResult(RESULT_OK, result);
        finish();
    }
}
