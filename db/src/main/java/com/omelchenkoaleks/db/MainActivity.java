package com.omelchenkoaleks.db;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText mInputField;
    ListView mNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.input_field_et);
        mNotesList = findViewById(R.id.notes_list_lv);
    }

    public void onOkButtonClick(View view) {
    }
}
