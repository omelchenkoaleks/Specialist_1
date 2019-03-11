package com.omelchenkoaleks.specialist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mClickMe;
    private Button mWebView;
    private Button mAnim;
    private Button mControls;
    private Button mDrawnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(
                MainActivity.this,
                "onCreate",
                Toast.LENGTH_SHORT).show();

        mAnim = findViewById(R.id.label_anim_btn);
        mAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimSample.class);
                startActivity(intent);
            }
        });

        mWebView = findViewById(R.id.web_view_btn);
        mWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        mClickMe = findViewById(R.id.button);
        mClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameLayoutActivity.class);
                startActivity(intent);
//                Toast.makeText(
//                        MainActivity.this,
//                        getString(R.string.message_btn),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        mControls = findViewById(R.id.control_btn);
        mControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ControlsSampleActivity.class);
                startActivity(intent);
            }
        });

        mDrawnButton = findViewById(R.id.drawn_enter_button);
        mDrawnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawnActivity.class);
                startActivity(intent);
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

    @Override
    public void finish() {
        super.finish();
        Toast.makeText(
                MainActivity.this,
                "finish",
                Toast.LENGTH_SHORT).show();
    }
}
