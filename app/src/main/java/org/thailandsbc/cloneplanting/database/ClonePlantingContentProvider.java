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
        matcher.addURI(Database.AUTHORITY, null                         , 0);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_FAMILY        , 1);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_LOG           , 2);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_SECTOR        , 3);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_SENTCLONE     , 4);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_RECEIVEDCLONE , 5);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_PLANTEDCLONE  , 6);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_LAND          , 7);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_ACTIVITY      , 8);
        matcher.addURI(Database.AUTHORITY, Database.TABLE_PICTURE       , 9);

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
                result = db.delete(Database.TABLE_FAMILY, selection,selectionArgs);
                break;
            case 2:
                result = db.delete(Database.TABLE_LOG, selection,selectionArgs);
                break;
            case 3:
                result = db.delete(Database.TABLE_SECTOR        , selection,selectionArgs);
                break;
            case 4:
                result = db.delete(Database.TABLE_SENTCLONE     , selection,selectionArgs);
                break;
            case 5:
                result = db.delete(Database.TABLE_RECEIVEDCLONE , selection,selectionArgs);
                break;
            case 6:
                result = db.delete(Database.TABLE_PLANTEDCLONE  , selection,selectionArgs);
                break;
            case 7:
                result = db.delete(Database.TABLE_LAND          , selection,selectionArgs);
                break;
            case 8:
                result = db.delete(Database.TABLE_ACTIVITY      , selection,selectionArgs);
                break;
            case 9:
                result = db.delete(Database.TABLE_PICTURE       , selection,selectionArgs);
                break;
            default:
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
            case 3:
                rowID = db.insert(Database.TABLE_SECTOR        , null, values);
                break;
            case 4:
                rowID = db.insert(Database.TABLE_SENTCLONE     , null, values);
                break;
            case 5:
                rowID = db.insert(Database.TABLE_RECEIVEDCLONE , null, values);
                break;
            case 6:
                rowID = db.insert(Database.TABLE_PLANTEDCLONE  , null, values);
                break;
            case 7:
                rowID = db.insert(Database.TABLE_LAND          , null, values);
                break;
            case 8:
                rowID = db.insert(Database.TABLE_ACTIVITY      , null, values);
                break;
            case 9:
                rowID = db.insert(Database.TABLE_PICTURE       , null, values);
                break;
            default:

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
            case 3:
                qb.setTables(Database.TABLE_SECTOR);
                break;
            case 4:
                qb.setTables(Database.TABLE_SENTCLONE);
                break;
            case 5:
                qb.setTables(Database.TABLE_RECEIVEDCLONE);
                break;
            case 6:
                qb.setTables(Database.TABLE_PLANTEDCLONE);
                break;
            case 7:
                qb.setTables(Database.TABLE_LAND);
                break;
            case 8:
                qb.setTables(Database.TABLE_ACTIVITY);
                break;
            case 9:
                qb.setTables(Database.TABLE_PICTURE);
                break;
            default:
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
                rowID = db.update(Database.TABLE_FAMILY, values, selection,selectionArgs);
                break;
            case 2:
                rowID = db.update(Database.TABLE_LOG, values, selection,selectionArgs);
                break;
            case 3:
                rowID = db.update(Database.TABLE_SECTOR        , values, selection,selectionArgs);
                break;
            case 4:
                rowID = db.update(Database.TABLE_SENTCLONE     , values, selection,selectionArgs);
                break;
            case 5:
                rowID = db.update(Database.TABLE_RECEIVEDCLONE , values, selection,selectionArgs);
                break;
            case 6:
                rowID = db.update(Database.TABLE_PLANTEDCLONE  , values, selection,selectionArgs);
                break;
            case 7:
                rowID = db.update(Database.TABLE_LAND          , values, selection,selectionArgs);
                break;
            case 8:
                rowID = db.update(Database.TABLE_ACTIVITY      , values, selection,selectionArgs);
                break;
            case 9:
                rowID = db.update(Database.TABLE_PICTURE       , values, selection,selectionArgs);
                break;
           default:
                break;

        }
        return rowID;
    }
}
