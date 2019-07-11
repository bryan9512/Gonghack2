package com.example.gonghack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBase.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBase.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){

        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }


    public long insertColumn(String camid, String name, String camaddress){
        ContentValues values = new ContentValues();
        values.put(DataBase.CreateDB.CAMID, camid);
        values.put(DataBase.CreateDB.CAM_NAME, name);
        values.put(DataBase.CreateDB.CAM_ADDRESS, camaddress);
        return mDB.insert(DataBase.CreateDB._TABLENAME0, null, values);
    }

    public Cursor selectColumns(){
        return mDB.query(DataBase.CreateDB._TABLENAME0,null,null,null,null,null,null);
    }

    public boolean exists(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name ='cam'" , null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    public boolean updateColumn(String userid, String name, String address){
        ContentValues values = new ContentValues();
        values.put(DataBase.CreateDB.CAMID, userid);
        values.put(DataBase.CreateDB.CAM_NAME, name);
        values.put(DataBase.CreateDB.CAM_ADDRESS, address);
        return mDB.update(DataBase.CreateDB._TABLENAME0, values, "camid=" + userid, null) > 0;
    }
}