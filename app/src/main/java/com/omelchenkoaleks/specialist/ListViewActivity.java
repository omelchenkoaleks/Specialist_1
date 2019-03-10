package com.omelchenkoaleks.specialist;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Так как мы наследуюемся от ListActivity, нам не нужен метод setContentView(),
         * потому-что ListView уже есть в активити от которого мы наследуемся...
         */

        Resources resources = getResources();
        String[] stationsArray = resources.getStringArray(R.array.stations);
        ArrayAdapter<String> stations = new ArrayAdapter<>(
                this, R.layout.list_item, stationsArray);
        setListAdapter(stations);

        // мы берем getListView(), потому-что наследуемся от ListActivity:
        ListView listStations = getListView();
        listStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent, // передается тот адаптер, на котором мы все делаем
                    View view, // тот тип вью, который является родительским в item.xml, конкретный на котором было нажатие
                    int position, // позиция внутри этого списка
                    long id) { // это может быть уникальный ключ к базе данных, идентификатор по которому можно найти объект
                CharSequence text = ((TextView) view).getText();
                int duration = Toast.LENGTH_LONG;
                Context context = getApplicationContext();
                Toast.makeText(context, text, duration).show();
            }
        });
    }
}
