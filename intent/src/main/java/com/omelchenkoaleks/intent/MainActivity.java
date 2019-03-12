package com.omelchenkoaleks.intent;

import android.content.Intent;
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
    TextView mSelectedStation;
    TextView mSelectedStationManifest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSelectedStation = findViewById(R.id.answer_station);
        mSelectedStationManifest = findViewById(R.id.selected_station);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CODE_SELECT_STATION) {
            if (resultCode == RESULT_OK && data != null) {
                mSelectedStation.setText(data
                        .getStringExtra(ListViewActivity
                                .EXTRA_SELECTED_STATION));
            } else {
                mSelectedStation.setText(getString(R.string.no_station_text));
            }
        }

        if (requestCode == CODE_SELECT_STATION_MANIFEST) {
            if (resultCode == RESULT_OK && data != null) {
                mSelectedStationManifest.setText(data
                        .getStringExtra(ListMetroPickerActivity
                                .EXTRA_SELECTED_STATION_MANIFEST));
            } else {
                mSelectedStationManifest.setText(R.string.no_station_text);
            }
        }
    }
}
