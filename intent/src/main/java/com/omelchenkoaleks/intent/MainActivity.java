package com.omelchenkoaleks.intent;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USER_NAME = "extra userName";
    public static final int CODE_SELECT_STATION = 1000;
    TextView mSelectedStation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSelectedStation = findViewById(R.id.answer_station);

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
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivityForResult(intent, CODE_SELECT_STATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_SELECT_STATION) {
            if (resultCode == RESULT_OK) {
                mSelectedStation.setText(data
                        .getStringExtra(ListViewActivity
                                .EXTRA_SELECTED_STATION));
            } else {
                mSelectedStation.setText(getString(R.string.no_station_text));
            }
        }
//        super.onActivityResult(requestCode, resultCode, data);
    }
}
