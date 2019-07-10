package com.example.gonghack;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract(){
    }

    public static class DBEntry implements BaseColumns{
        public static final String TABLE_NAME="Cam";
        public static final String COLUMN_NAME_TITLE="title";
        public static final String COLUMN_NAME_ADDRESS="address";
    }
}
