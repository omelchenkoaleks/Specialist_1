package com.omelchenkoaleks.intent;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity
        implements AdapterView.OnItemClickListener {

    public static final String EXTRA_SELECTED_STATION = "extra selectedStation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        result.putExtra(EXTRA_SELECTED_STATION, stationSelected);
        setResult(RESULT_OK, result);
        finish();
    }
}
