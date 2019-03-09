package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FrameLayoutActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        text = findViewById(R.id.label);
    }

    public void onClick(View view) {
        EditText userName = findViewById(R.id.userName);
        switch (view.getId()) {
            case R.id.ok_button:
                String text = userName.getText().toString().trim();
                if (text.length() > 0) {
                    Toast.makeText(this, getString(R.string.hello_prefix) + " " + text,
                            Toast.LENGTH_LONG).show();
                }
                userName.setText(null);
                break;
            case R.id.cancel_button:
                userName.setText(null);
                break;
            default:
                Toast.makeText(this, getString(R.string.oops_msg),
                        Toast.LENGTH_LONG).show();
        }
    }
}
