package com.omelchenkoaleks.shared;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        /**
         * Для того, чтобы контекстное меню работало - нужно зарегистрировать тот объект,
         * для которого оно будет работать... В данном случае ListView...
         */
        registerForContextMenu(getListView());
    }

    /**
     * При нажатии на пункт с контекстным меню будет вызван этот метод
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // создаем объекты из разметки...
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_station, menu);
        // забираем из menuInfo информацию, касающуюся нашего адаптера, для того,
        // чтобы потом можно было получить что-то конкретное...
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        // получаем "эту" конкретную инфо...
        CharSequence stationSelected = getStationFromMenuInfo(info);
        // и эту информацию мы устанавливаем в качестве подзаголовка...
        menu.setHeaderTitle(stationSelected);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_send:
                // пользуясь адаптером можем вытащить инфо из пункта меню, который был передан...
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item
                                .getMenuInfo();
                // получаем эту информацию о станции...
                CharSequence stationSelected = getStationFromMenuInfo(info);
                // и посылаем эту информацию...
                sendStation(stationSelected);
                return true;
            case R.id.item_exit:
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CharSequence stationSelected = ((TextView) view).getText();
        sendStation(stationSelected);
    }

    private void sendStation(CharSequence acceptTextView) {
        Intent result = new Intent();
        result.putExtra(EXTRA_RESULT_STATION_TEXT_VIEW, acceptTextView);
        setResult(RESULT_OK, result);
        finish();
    }

    private CharSequence getStationFromMenuInfo(AdapterView.AdapterContextMenuInfo info) {
        TextView textView = (TextView) info.targetView;
        return textView.getText();
    }
}
