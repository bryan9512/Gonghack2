package com.example.gonghack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sInstance;

    private static final int DB_VERSION =1;
    private static final String DB_NAME="Cam.db";
    private static final String SQL_CREATE_ENTRIES=
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT",
            DBContract.DBEntry.TABLE_NAME,
            DBContract.DBEntry._ID,
            DBContract.DBEntry.COLUMN_NAME_TITLE,
            DBContract.DBEntry.COLUMN_NAME_ADDRESS);

    private  static final String SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS "+DBContract.DBEntry.TABLE_NAME;

    public static DBHelper getInstance(Context context){
        if (sInstance==null){
            sInstance=new DBHelper(context);
        }
        return sInstance;
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);//생성용 SQL문법

    }

    //버전이 올라가면 실행되는거임!
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }


}
