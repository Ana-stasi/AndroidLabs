package com.kpi.androidlabs2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "resultstore.db";
    private static final int DB_VERSION = 1;
    static final String RESULT_TABLE = "results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RESULT = "result";

    private static final String SQL_CREATE_RESULT_TABLE ="CREATE TABLE "+RESULT_TABLE +" (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_RESULT
            + " INTEGER);";
    private static final String SQL_REMOVE_RESULT_TABLE = "DROP TABLE IF EXISTS "+RESULT_TABLE;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESULT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_REMOVE_RESULT_TABLE);
        onCreate(db);
    }
}
