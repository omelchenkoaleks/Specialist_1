package com.omelchenkoaleks.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String userName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME);
        // делаем проверку на null, потому что может вернуться пустое значение
        userName = (userName == null) ? "Unknown" : userName;

        ((TextView) findViewById(R.id.greeting))
                .setText(getString(R.string.hello_text) + " " + userName);
    }
}
