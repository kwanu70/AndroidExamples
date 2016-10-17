package com.example.kwanwoo.sqlitedbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kwanwoo on 2016-10-17.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="schedule.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE schedule (" +
                "_id INTEGER  NOT NULL PRIMARY KEY," +
                "title TEXT  NULL," +
                "datetime DATETIME  NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }
}
