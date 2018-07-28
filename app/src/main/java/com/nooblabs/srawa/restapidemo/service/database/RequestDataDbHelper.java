package com.nooblabs.srawa.restapidemo.service.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RequestDataDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "test_db.db";


    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + RequestDataHelper.RequestEntry.TABLE_NAME + " (" +
                    RequestDataHelper.RequestEntry._ID + " INTEGER PRIMARY KEY," +
                    RequestDataHelper.RequestEntry.COLUMN_FIRST_NAME + " TEXT, " +
                    RequestDataHelper.RequestEntry.COLUMN_LAST_NAME + " TEXT, " +
                    RequestDataHelper.RequestEntry.COLUMN_PHONE + " TEXT, " +
                    RequestDataHelper.RequestEntry.COLUMN_ADDRESS + " TEXT, " +
                    RequestDataHelper.RequestEntry.COLUMN_RESTAURANT_NAME + " TEXT, " +
                    RequestDataHelper.RequestEntry.COLUMN_REQUEST_BY + " INTEGER )";

    private static final String SQL_DELETE_DATA =
            "DROP TABLE IF EXISTS " + RequestDataHelper.RequestEntry.TABLE_NAME;

    public RequestDataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATA);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
