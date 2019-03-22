package com.omelchenkoaleks.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements BrowserCall {
    public static final String KEY_INDEX = "key index";
    public static final String URL_PREFIX = "http://";
    public static final CharSequence[] ENGINE_NAME = { "Google", "Yahoo", "Yandex", };
    public static final String[] ENGINE_URL = { "google.com", "yahoo.com", "yandex.com", };
    public static final String ACTION_ENGINE_SELECTED =
            "com.omelchenkoaleks.dialog.intent.action.ENGINE_SELECTED";

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * На приемной стороне мы вытаскиваем ключ.
             * Указываем -1, как значение по умолчанию, если поля с ключем нет.
             * Зовем браузер...
             */
            int index = intent.getIntExtra(KEY_INDEX, -1);
            callBrowser(index);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // регистрируем приемник...
        IntentFilter intentFilter = new IntentFilter(ACTION_ENGINE_SELECTED);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public void onClick(View view) {
        DialogFragment dialogFragment = new DialogSample();
        dialogFragment.show(getSupportFragmentManager(), "DialogSample");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // когда завершаемся, нужно приемник разрегистрировать...
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void callBrowser(int index) {
        if (index >= 0) {
            Uri webpage = Uri.parse(URL_PREFIX + ENGINE_URL[index]);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
