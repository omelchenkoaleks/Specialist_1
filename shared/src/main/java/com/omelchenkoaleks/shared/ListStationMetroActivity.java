package com.omelchenkoaleks.shared;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListStationMetroActivity extends ListActivity
        implements AdapterView.OnItemClickListener {
    public static final String EXTRA_RESULT_STATION_TEXT_VIEW = "accept text for ListView";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = getResources();
        String[] stationList = resources.getStringArray(R.array.stations);
        ArrayAdapter<String> stationAdapter = new ArrayAdapter<>(
                this, R.layout.list_station_item, stationList);
        setListAdapter(stationAdapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CharSequence acceptTextView = ((TextView) view).getText();
        Intent result = new Intent();
        result.putExtra(EXTRA_RESULT_STATION_TEXT_VIEW, acceptTextView);
        setResult(RESULT_OK, result);
        finish();
    }
}
