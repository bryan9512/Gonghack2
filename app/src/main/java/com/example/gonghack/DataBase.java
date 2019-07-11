package com.example.gonghack;

import android.provider.BaseColumns;

public final class DataBase{

    public static final class CreateDB implements BaseColumns{
    public static final String CAMID="camid";
    public static final String CAM_NAME="camname";
    public static final String CAM_ADDRESS="camaddress";
    public static final String _TABLENAME0="cam";
    public static final String _CREATE0="create table if not exists "+_TABLENAME0+"("
            +_ID+" integer primary key autoincrement, "
            +CAMID+" text not null , "
            +CAM_NAME+" text not null , "
            +CAM_ADDRESS+ " integer not null );";

    }
}
