package com.omelchenkoaleks.specialist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FrameLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        Button mShowList = findViewById(R.id.list_start_btn);
        mShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrameLayoutActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

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
