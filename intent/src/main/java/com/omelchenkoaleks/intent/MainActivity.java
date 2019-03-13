package com.omelchenkoaleks.intent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USER_NAME = "extra userName";
    public static final String ECTION_PICK_STATION =
            "com.omelchenkoaleks.intent.intent.action.PICK_METRO_STATION";
    public static final int CODE_SELECT_STATION = 1000;
    public static final int CODE_SELECT_STATION_MANIFEST = 1001;

    // для работы с SharedPreferences
    // 1-е: нам нужно имя файла
    public static final String PREFERENCES = "preferences";
    // 2-е: нам нужен какой-то ключ
    public static final String KEY_STATION = "selected station";
    public static final String PREFERENCES_MANIFEST = "preferences manifest";
    public static final String KEY_STATION_MANIFEST = "selected station manifest";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences mSharedPreferencesManifest;

    private TextView mSelectedStation;
    private TextView mSelectedStationManifest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSelectedStation = findViewById(R.id.answer_station);
        mSelectedStationManifest = findViewById(R.id.selected_station);

        // Сохраняем данные в SharedPreferences
        mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String selectedStation = mSharedPreferences.getString(KEY_STATION, null);
        if (selectedStation != null) {
            mSelectedStation.setText(selectedStation);
        } else {
            mSelectedStation.setText(R.string.no_station_text);
        }

        // инициализируем для того, чтобы можно было забирать данные из SharedPreferences
        // сразу при инициализации...
        mSharedPreferencesManifest = getSharedPreferences(PREFERENCES_MANIFEST, MODE_PRIVATE);
        String selectedStationManifest = mSharedPreferencesManifest.getString(KEY_STATION_MANIFEST, null);
        if (selectedStationManifest != null) {
            mSelectedStationManifest.setText(selectedStationManifest);
        } else {
            mSelectedStationManifest.setText(R.string.no_station_text);
        }

        // так мы можем посмотреть какое действие имеет интент этого Астивити
        Intent intent = getIntent();
        String action = intent.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
    }

    public void onClickIntent(View view) {
        EditText input = findViewById(R.id.userName);
        String userName = input.getText().toString().trim();
        if (userName.length() > 0) {
            Intent intent = new Intent(this, SecondActivity.class);
            // вложив это таким образом = на приемной стороне мы сможет это вытащить
            intent.putExtra(EXTRA_USER_NAME, userName);
            startActivity(intent);
        }
        // поле нужно после работы зачистить
        input.setText(null);
    }

    public void onChooseStationClick(View view) {
        switch (view.getId()) {
            case R.id.choose_station:
                Intent intent = new Intent(this, ListViewActivity.class);
                // выполняем полезную проверку
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CODE_SELECT_STATION);
                } else {
                    Toast.makeText(this, getString(R.string.no_text), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.select_station:
                Intent intentSelect = new Intent(ECTION_PICK_STATION);
                // выполняем полезную проверку
                if (intentSelect.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentSelect, CODE_SELECT_STATION_MANIFEST);
                } else {
                    Toast.makeText(this, getString(R.string.no_text), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "No )))", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  Этот метод начинает работу тогда, когда та активити, которая была вызвана с помощью Intent
     *  (вторая активити, допустим), завершает свою работу и сюда возращается результат...
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // requestCode - проверяем: а, та ли это астивити???
        if (requestCode == CODE_SELECT_STATION) {
            // т.к. у нас есть необходимость сохранить данные = мы получаем редактор
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            if (resultCode == RESULT_OK && data != null) {
                // значение берется из того самого Intent, который нам вернула "вторая активити"
                String selectedStation = data
                        .getStringExtra(ListViewActivity.EXTRA_SELECTED_STATION);
                // полученное значение мы тут записываем, а ниже мы еге еще и сохраняем с помощью редактора
                mSelectedStation.setText(selectedStation);
                // редактор по нужному ключу будет сохранять нужное значение,
                // а откуда берется значение? Выше написано:
                editor.putString(KEY_STATION, selectedStation);
            } else {
                mSelectedStation.setText(getString(R.string.no_station_text));
                // если же ничего не было выбрано, то этот ключ не нужен мы его удалаяем
                editor.remove(KEY_STATION);
            }
            editor.apply();
        }

        if (requestCode == CODE_SELECT_STATION_MANIFEST) {
            SharedPreferences.Editor editorManifest = mSharedPreferencesManifest.edit();
            if (resultCode == RESULT_OK && data != null) {
                String selectedStationManifest = data
                        .getStringExtra(ListMetroPickerActivity.EXTRA_SELECTED_STATION_MANIFEST);
                mSelectedStationManifest.setText(selectedStationManifest);
                // говорим редактору: положи в коробочку с нашим ключем это значение
                editorManifest.putString(KEY_STATION_MANIFEST, selectedStationManifest);
            } else {
                mSelectedStationManifest.setText(R.string.no_station_text);
                editorManifest.remove(KEY_STATION_MANIFEST);
            }
            editorManifest.apply();
        }
    }
}
