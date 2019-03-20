package com.omelchenkoaleks.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "notes.db";
    static final String DB_TABLE = "notes";
    static final String COLUMN_NOTE = "note";

    private static final String DB_CREATE = "CREATE TABLE "
            + DB_TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOTE + " TEXT NOT NULL);";

    private Context mContext;

    DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        // TODO: delete after debugging
        ContentValues values = new ContentValues(1);
        for (int i = 0; i < 5; i++) {
            values.put(COLUMN_NOTE, "Note #" + i);
            db.insert(DB_TABLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
