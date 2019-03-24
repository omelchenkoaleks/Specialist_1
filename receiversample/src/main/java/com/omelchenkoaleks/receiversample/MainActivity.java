package com.omelchenkoaleks.receiversample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = getIntent().getStringExtra(NewReceiver.KEY_TEXT);
        TextView tv = findViewById(R.id.text_tv);
        tv.setText(text);
    }
}
