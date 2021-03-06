package com.omelchenkoaleks.dialogfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BrowserCall {
    static final String[] ENGINE_NAMES = {"Google", "Yahoo", "Yandex",};
    static final CharSequence[] ENGINES_URLS = {"google.com", "yahoo.com", "yandex.com",};
    public static final String URL_PREFIX = "http://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callBrowser(int index) {
        if (index < ENGINES_URLS.length) {
            Uri webpage = Uri.parse(URL_PREFIX + ENGINES_URLS[index]);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_1_bt:
                DialogFragment_1 dialogFragment_1 = new DialogFragment_1();
                dialogFragment_1.show(getSupportFragmentManager(), "Dialog One");
                break;
            case R.id.select_2_bt:
                DialogFragment_2 dialogFragment_2 = new DialogFragment_2();
                dialogFragment_2.show(getSupportFragmentManager(), "Dialog Two");
                break;
            default:
                Toast.makeText(this, getString(R.string.text_oops),
                        Toast.LENGTH_SHORT).show();
        }
    }
}
