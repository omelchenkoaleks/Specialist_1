package com.omelchenkoaleks.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(050)506-01-48"));
        // проверяем есть ли что-то, что выполнит наш интент
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
    }
}
