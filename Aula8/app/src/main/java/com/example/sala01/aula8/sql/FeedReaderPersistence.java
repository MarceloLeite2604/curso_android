package com.example.sala01.aula8.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sala01 on 23/09/2016.
 */

public class FeedReaderPersistence {

    private Activity activity;
    private FeedReaderDbHelper feedReaderDbHelper;

    public FeedReaderPersistence(Activity activity) {
        this.activity = activity;
        this.feedReaderDbHelper = new FeedReaderDbHelper(this.activity);
    }

    public long insert(String property, String value) {
        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PROPERTY, property);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_VALUE, value);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    public void delete(String property) {

        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String deleteSelection = FeedReaderContract.FeedEntry.COLUMN_NAME_PROPERTY + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] deleteSelectionArgs = {property};
        // Issue SQL statement.
        sqLiteDatabase.delete(FeedReaderContract.FeedEntry.TABLE_NAME, deleteSelection, deleteSelectionArgs);
    }

    public String select(String property) {

        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PROPERTY,
                FeedReaderContract.FeedEntry.COLUMN_NAME_VALUE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_PROPERTY + " = ?";
        String[] selectionArgs = {property};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_PROPERTY + " DESC";

        Cursor cursor = sqLiteDatabase.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        cursor.moveToFirst();
        String value = cursor.getString(
                cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_VALUE)
        );
        return value;
    }
}
