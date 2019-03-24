package com.omelchenkoaleks.receiversample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_SEND_TIMESTAMP =
            "com.omelchenkoaleks.receiver.intent.action.SEND_TIMESTAMP";
    public static final String KEY_TIMESTAMP = "timestamp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = getIntent().getStringExtra(NewReceiver.KEY_TEXT);
        TextView tv = findViewById(R.id.text_tv);
        tv.setText(text);

        Intent intent = new Intent(ACTION_SEND_TIMESTAMP);
        intent.putExtra(KEY_TIMESTAMP, System.currentTimeMillis());
        sendBroadcast(intent);
    }
}
