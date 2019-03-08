package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(
                MainActivity.this,
                "onCreate",
                Toast.LENGTH_SHORT).show();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        getString(R.string.message_btn),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(
                MainActivity.this,
                "onStart",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(
                MainActivity.this,
                "onResume",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(
                MainActivity.this,
                "onPause",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(
                MainActivity.this,
                "onStop",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(
                MainActivity.this,
                "onRestart",
                Toast.LENGTH_SHORT).show();
    }
}
