package com.omelchenkoaleks.dialogfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String[] ENGINE_NAMES = {"Google", "Yahoo", "Yandex",};
    static final CharSequence[] ENGINES_URLS = {"google.com", "yahoo.com", "yandex.com",};
    public static final String URL_PREFIX = "http://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void callBrowser(int index) {
        Uri webpage = Uri.parse(URL_PREFIX + ENGINES_URLS[index]);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_1_bt:
                callBrowser(0);
                break;
            case R.id.select_2_bt:
                callBrowser(1);
                break;
            default:
                Toast.makeText(this, getString(R.string.text_oops),
                        Toast.LENGTH_SHORT).show();
        }
    }
}
