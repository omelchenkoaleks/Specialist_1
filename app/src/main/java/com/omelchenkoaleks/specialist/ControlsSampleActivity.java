package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ControlsSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_sample);
    }

    public void onCheckboxClicked(View view) {
        if (((CheckBox) view).isChecked()) {
            Toast.makeText(this, "Marked!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not noted!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(this, getString(R.string.included),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.switched_off),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        RadioButton radioButton = (RadioButton) view;
        Toast.makeText(this,
                getString(R.string.animal_selected_prefix) + " " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
