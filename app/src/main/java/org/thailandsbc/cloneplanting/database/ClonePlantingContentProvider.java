package org.thailandsbc.cloneplanting.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ClonePlantingContentProvider extends ContentProvider {

    MySQLiteOpenHelper mySQLiteOpenHelper;

    static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(Database.AUTHORITY, null, 0);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_FAMILY, 1);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_LOG, 2);
    }

    public ClonePlantingContentProvider() {
        super();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        int result = 0;
        int x = matcher.match(uri);
        switch (x) {
            case 0:
                break;
            case 1:
                result = db.delete(Database.TABLE_FAMILY, selection,
                        selectionArgs);
                break;
            case 2:
                result = db.delete(Database.TABLE_LOG, selection,
                        selectionArgs);
                break;
        }

        return result;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        long rowID = 1;
        int x = matcher.match(uri);
        switch (x) {
            case 0:
                break;
            case 1:
                rowID = db.insert(Database.TABLE_FAMILY, null, values);
                break;
            case 2:
                rowID = db.insert(Database.TABLE_LOG, null, values);
                break;
        }
        return ContentUris.withAppendedId(uri, rowID);
    }

    @Override
    public boolean onCreate() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), Database.AUTHORITY, null, 1);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        int x = matcher.match(uri);
        switch (x) {
            case 0:
                break;
            case 1:
                qb.setTables(Database.TABLE_FAMILY);
                break;
            case 2:
                qb.setTables(Database.TABLE_LOG);
                break;

        }
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        return qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        int rowID = 1;
        int x = matcher.match(uri);
        switch (x) {
            case 0:
                break;
            case 1:
                rowID = db.update(Database.TABLE_FAMILY, values, selection,
                        selectionArgs);
                break;
            case 2:
                rowID = db.update(Database.TABLE_LOG, values, selection,
                        selectionArgs);
                break;
        }
        return rowID;
    }
}
