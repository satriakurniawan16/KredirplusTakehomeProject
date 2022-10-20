package com.satriakurniawandicoding.submission3.view.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.TABLE_NAME;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movieDatabase";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.MovieColumns.ID,
            DatabaseContract.MovieColumns.IMAGE,
            DatabaseContract.MovieColumns.TITLE,
            DatabaseContract.MovieColumns.DESCRIPTION,
            DatabaseContract.MovieColumns.DATE,
            DatabaseContract.MovieColumns.TYPE
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
