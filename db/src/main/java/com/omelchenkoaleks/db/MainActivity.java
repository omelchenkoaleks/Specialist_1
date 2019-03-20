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
    private String mOldNote;
    private long mNoteId = -1;

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
            case R.id.item_edit: {
                /**
                 * логика:
                 * когда выбираем элемент для редактирования, его сначала помещаем в EditText -
                 * там редактируем и нажимаем ok...
                 */
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                mOldNote = getNoteById(info.id); // save for future use
                mInputField.setText(mOldNote);   // fill in input field
                mNoteId = info.id;               // save for future use
                return true;
            }
            case R.id.item_delete: {
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                deleteNote(info.id);
                showNotes();
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }

    private String getNoteById(long id) {
        mSQLiteDatabase = (mSQLiteDatabase == null)
                ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
        Cursor cursor = mSQLiteDatabase.query(DbOpenHelper.DB_TABLE, FIELDS, "_id = " + id,
                null, null, null, null);

        String note = null;
        // может оказаться так, что по каким-то причинам курсор может оказаться null, поэтому проверка...
        if (cursor != null) {
            /**
             * ВАЖНО:
             * Курсор на первое место? Почему?
             * т.е. ставить в первую позицию в полученном результате...
             * это означает, что его нужно правильно позиционировать - чтобы
             * он стал в начало нашего списка (записи)...
             */
            cursor.moveToFirst();
            // курсор готов отдавать нужное поле из конкретной записи только по индексу, а
            // индекс мы можем получить, спросив, а какой индекс имеет поле с таким именем...
            note = cursor.getString(
                    cursor.getColumnIndexOrThrow(DbOpenHelper.COLUMN_NOTE));
            cursor.close();
        }
        return note;
    }

    private void deleteNote(long id) {
        mSQLiteDatabase = (mSQLiteDatabase == null)
                ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
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
        // в самом начале получаем текстовое поле...
        String note = mInputField.getText().toString().trim();

        // только если длина записи, очищенная от пробелов больше нуля...
        if (note.length() > 0) {
            // наполняем объект ContentValues нужным значением...
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(DbOpenHelper.COLUMN_NOTE, note);
            // получаем бд
            mSQLiteDatabase = (mSQLiteDatabase == null)
                    ? mDbOpenHelper.getWritableDatabase() : mSQLiteDatabase;
            // и в случае если это обновление, тогда...
            if (mNoteId >= 0) {
                /**
                 * Третий параметр = условие, запись, которая будет обновлена...
                 */
                mSQLiteDatabase.update(DbOpenHelper.DB_TABLE,
                        contentValues, "_id = " + mNoteId, null);
            } else {
                // если не обновление, тогда просто втавляем...
                mSQLiteDatabase.insert(DbOpenHelper.DB_TABLE, null, contentValues);
            }
            showNotes();
        }

        // сбрасываем все поля (зачистку делаем)...
        mNoteId = -1;
        mOldNote = null;
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