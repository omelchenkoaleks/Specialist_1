package com.omelchenkoaleks.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_SEND_TIMESTAMP =
            "com.omelchenkoaleks.receiver.intent.action.SEND_TIMESTAMP";
    private static final String ACTION_SEND_INTENT_TEXT =
            "com.omelchenkoaleks.receiver.intent.action.SEND_TEXT";
    private static final String KEY_TEXT = "key text";

    TextView mTimestamp;

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long time = intent.getLongExtra("timestamp", 0);
            if (time != 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               mTimestamp.setText(simpleDateFormat.format(time));
            }
        }
    };

    EditText mInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.enter_et);
        mTimestamp = findViewById(R.id.timestamp_tv);

        registerReceiver(mBroadcastReceiver, new IntentFilter(ACTION_SEND_TIMESTAMP));
    }

    public void onClick(View view) {
        String text = mInputField.getText().toString().trim();
        if (text.length() > 0) {
            Intent intent = new Intent(ACTION_SEND_INTENT_TEXT);
            intent.putExtra(KEY_TEXT, text);
            sendBroadcast(intent);
        }
        mInputField.setText(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
