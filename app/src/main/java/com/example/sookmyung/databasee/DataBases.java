package com.example.sookmyung.databasee;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DOSU = "dosu";
        public static final String COUNTRY = "country";
        public static final String CATEGORY = "category";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +ID+" text not null , "
                +NAME+" text not null , "
                +DOSU+" integer not null , "
                +COUNTRY+" integer not null , "
                +CATEGORY+" text not null );";
    }


}
