package com.satriakurniawandicoding.submission3.view.database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {
    public static final String AUTHORITY = "com.satriakurniawandicoding.submission3";
    private static final String SCHEME = "content";

    private DatabaseContract(){}

    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_NAME = "favoritemovie";
        public static final String ID = "id";
        public static final String IMAGE = "image";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
        public static final String TYPE = "type";

    }
}
