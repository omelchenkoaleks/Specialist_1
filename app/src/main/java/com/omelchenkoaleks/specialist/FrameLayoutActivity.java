package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FrameLayoutActivity extends AppCompatActivity {
    TextView name;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        name = findViewById(R.id.label);
        editName = findViewById(R.id.userName);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_button:
                String text = editName.getText().toString().trim();
                if (text.length() > 0) {
                    Toast.makeText(this, "Hello " + text, Toast.LENGTH_LONG).show();
                }
                editName.setText(null);
                break;
            case R.id.cancel_button:
                editName.setText(null);
                break;
            default:
                Toast.makeText(this, "OOPS, something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
