package com.omelchenkoaleks.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        // регистрируем контекстное меню...
        registerForContextMenu(mNotesList);
        registerForContextMenu(mInputField);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.notes_list_lv:
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.notes_menu, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_edit:
                return true;
            case R.id.item_delete:
                // создаем объект (info) из которого можно вытащить нужный id объекта, чтобы
                // можно было удалить тот, уже не нужный, объект...
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                deleteNote(info.id);
                // обновляем список...
                showNotes();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteNote(long id) {
        // получаем бд
        mSQLiteDatabase = (mSQLiteDatabase == null)
                ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
        // удаляем из таблицы в бд
        mSQLiteDatabase.delete(DbOpenHelper.DB_TABLE, "_id = " + id, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
            mSQLiteDatabase = null;
        }
    }

    public void onOkButtonClick(View view) {
        String newNote = mInputField.getText().toString().trim();
        if (newNote.length() > 0) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(DbOpenHelper.COLUMN_NOTE, newNote);
            mSQLiteDatabase = (mSQLiteDatabase == null)
                    ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
            mSQLiteDatabase.insert(DbOpenHelper.DB_TABLE, null, contentValues);
            showNotes();
        }
        mInputField.setText(null);
    }

    private void showNotes() {
        mSQLiteDatabase = (mSQLiteDatabase == null)
                ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
        Cursor cursor = mSQLiteDatabase.query(DbOpenHelper.DB_TABLE, FIELDS,
                null, null, null, null, ORDER);
        mSimpleCursorAdapter.swapCursor(cursor);
    }
}
