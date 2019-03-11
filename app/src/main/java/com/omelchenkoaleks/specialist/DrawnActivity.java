package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawnActivity extends AppCompatActivity implements View.OnClickListener {

    private int mButtonNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawn);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.drawn_button) {
            Button newButton = new Button(this);
            newButton.setOnClickListener(this);
            newButton.setText(getString(R.string.button_no_prefix) + " " + mButtonNo++);
            ((LinearLayout) view.getParent()).addView(newButton);
        } else {
            ((LinearLayout) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
