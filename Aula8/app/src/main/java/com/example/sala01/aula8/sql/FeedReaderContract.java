package com.example.sala01.aula8.sql;

import android.provider.BaseColumns;

/**
 * Created by sala01 on 23/09/2016.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tabela";
        public static final String COLUMN_NAME_PROPERTY = "property";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}