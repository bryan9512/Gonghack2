package com.example.gonghack;

import android.provider.BaseColumns;

public final class Database {

    public static final class CreateDB implements BaseColumns {
        public static final String EXIST = "exist";
        public static final String NAMEONE = "nameone";
        public static final String NAMETWO = "nametwo";
        public static final String NAMETHREE = "namethree";
        public static final String NAMEFOUR = "namefour";
        public static final String NAMEFIVE = "namefive";
        public static final String URLONE = "urlone";
        public static final String URLTWO = "urltwo";
        public static final String URLTHREE= "urlthree";
        public static final String URLFOUR = "urlfour";
        public static final String URLFIVE = "urlfive";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +EXIST+" text not null , "
                +NAMEONE+" text not null , "
                +URLONE+" text not null , "
                +NAMETWO+" text not null , "
                +URLTWO+" text not null , "
                +NAMETHREE+" text not null , "
                +URLTHREE+" text not null , "
                +NAMEFOUR+" text not null , "
                +URLFOUR+" text not null , "
                +NAMEFIVE+" text not null , "
                +URLFIVE+" text not null );";
    }

}
