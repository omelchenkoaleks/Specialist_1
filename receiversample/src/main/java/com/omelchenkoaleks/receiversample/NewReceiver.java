package com.omelchenkoaleks.receiversample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NewReceiver extends BroadcastReceiver {
    public static final String KEY_TEXT = "key text";

    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(KEY_TEXT);
        if (text != null) {
            Intent i = new Intent(context, MainActivity.class);
            // нужно установить этот флаг для создания стека новых задач - это важно, так как
            // эти два приложения должны быть связаны ...
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(KEY_TEXT, text);
            context.startActivity(i);
        }
    }
}
