package com.omelchenkoaleks.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_SEND_INTENT_TEXT =
            "com.omelchenkoaleks.receiver.intent.action.SEND_TEXT";
    private static final String KEY_TEXT = "key text";

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(KEY_TEXT);
            if (text != null) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    };

    EditText mInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.enter_et);

        registerReceiver(mBroadcastReceiver, new IntentFilter(ACTION_SEND_INTENT_TEXT));
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
