package com.omelchenkoaleks.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String[] FIELDS = {"_id", DbOpenHelper.COLUMN_NOTE,};
    private static final String[] FROM = {DbOpenHelper.COLUMN_NOTE,};
    private static final int[] TO = {R.id.note_tv,};
    private static final String ORDER = "_id DESC";

    EditText mInputField;
    ListView mNotesList;

    SimpleCursorAdapter mSimpleCursorAdapter;
    DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);

    SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.input_field_et);
        mNotesList = findViewById(R.id.notes_list_lv);

        mSimpleCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.note, null, FROM, TO, 0);

        mNotesList.setAdapter(mSimpleCursorAdapter);
    }

    /**
     * В этом методе мы подключаемся к нашей базе данных, но что будет, если наше приложение
     * будет скрыто? Она будет продолжать потреблять ресурсы - поэтому...
     * В методе onStop() мы ее закрываем... null = чтобы сборщик мусора ее почистил...
     */
    @Override
    protected void onResume() {
        super.onResume();
        mSQLiteDatabase = mDbOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.query(DbOpenHelper.DB_TABLE, FIELDS,
                null, null,null, null, ORDER);
        mSimpleCursorAdapter.swapCursor(cursor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
            mSQLiteDatabase = null;
        }
    }

    public void onOkButtonClick(View view) {}
}
