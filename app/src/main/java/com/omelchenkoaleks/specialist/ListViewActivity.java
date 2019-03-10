package com.omelchenkoaleks.specialist;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = getResources();
        String[] stationsArray = resources.getStringArray(R.array.stations);
        ArrayAdapter<String> stations = new ArrayAdapter<>(
                this, R.layout.list_item, stationsArray);
        setListAdapter(stations);
    }
}
