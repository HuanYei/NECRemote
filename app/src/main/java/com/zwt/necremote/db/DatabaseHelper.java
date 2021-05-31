package com.zwt.necremote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static DatabaseHelper mySQLiteOpenHelper;

    private static final String DB_NAME = "person_info.db";
    private static final int VERSION = 1;

    public synchronized static DatabaseHelper getInstance(Context context) {
        if (null == mySQLiteOpenHelper) {
            mySQLiteOpenHelper = new DatabaseHelper(context, DB_NAME, null, VERSION);
        }
        return mySQLiteOpenHelper;
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table NECIR(IRNAME varchar(20),IRKEY varchar(20),IRCODE varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
