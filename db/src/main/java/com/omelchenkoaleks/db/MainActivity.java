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
    /**
     * заметочка:
     * запятая указана для указания, что фигурные скобки используются для масива, а
     * не для выделения блоков...
     */
    private static final String[] FIELDS = {"_id", DbOpenHelper.COLUMN_NOTE,};
    // Указываем то поле, которое есть (откуда взять)...
    private static final String[] FROM = {DbOpenHelper.COLUMN_NOTE,};
    // Указываем по id то место, куда нужно вставить...
    private static final int[] TO = {R.id.note_tv,};
    private static final String ORDER = "_id DESC";

    EditText mInputField;
    ListView mNotesList;

    SimpleCursorAdapter mSimpleCursorAdapter;
    DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);

    // Базу данных нужно получать только тогда, когда она действительно потребуется...
    // а, когда это? Когда на экране уже что-то нарисовано... onResume()...
    SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.input_field_et);
        mNotesList = findViewById(R.id.notes_list_lv);

        mSimpleCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.note, null, FROM, TO, 0);
        /**
         * КОМЕНТАРИИ ПО ПАРАМЕТРАМ ВЫШЕ:
         * 1, контекст
         * 2, специально созданный для адаптера, который он будет использовать для каждой строчки
         * 3, Cursor пока нет, поэтому написал null
         * 4, адаптеру нужно знать какие поля из базы данных вставлять в места разметки
         * 5, адаптеру нужно указать куда вставлять данные - указываем
         * 6, флаги
         */

        // привязываем адаптер
        mNotesList.setAdapter(mSimpleCursorAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // делаем запрос к базе данных: сначала получаем записываемую базу данных...
        mSQLiteDatabase = mDbOpenHelper.getWritableDatabase();
        // теперь получаем объект типа Cursor...
        Cursor cursor = mSQLiteDatabase.query(DbOpenHelper.DB_TABLE, FIELDS,
                null, null,null, null, ORDER);
        // и прикрепляем курсор к нашему адаптеру...
        mSimpleCursorAdapter.swapCursor(cursor);
    }

    public void onOkButtonClick(View view) {}
}
