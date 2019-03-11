package com.omelchenkoaleks.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "extra userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
