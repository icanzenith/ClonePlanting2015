package org.thailandsbc.cloneplanting.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.thailandsbc.cloneplanting.model.ColumnName;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                              DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAMILY);
        db.execSQL(CREATE_TABLE_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private final String CREATE_TABLE_FAMILY ="" +
            "CREATE TABLE familyTable(" +
           ColumnName.FamilyTable._ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
           ColumnName.FamilyTable.FamilyCode   + " TEXT," +
           ColumnName.FamilyTable.objectId     + " INTEGER," +
           ColumnName.FamilyTable.SendAmount   + " INTEGER," +
           ColumnName.FamilyTable.SenderID     + " TEXT," +
           ColumnName.FamilyTable.SendTo       + " TEXT," +
           ColumnName.FamilyTable.ReceiveAmount+ " INTEGER," +
           ColumnName.FamilyTable.ReceiverID   + " INTEGER," +
           ColumnName.FamilyTable.PlantAmount  + " INTEGER," +
           ColumnName.FamilyTable.PlanterID    + " INTEGER," +
           ColumnName.FamilyTable.LeftAmount   + " INTEGER," +
           ColumnName.FamilyTable.LeftCheckerID + " INTEGER," +
           ColumnName.FamilyTable.RowNumber    + " INTEGER," +
           ColumnName.FamilyTable.OrderInRow   + " INTEGER," +
           ColumnName.FamilyTable.LandID       + " TEXT," +
           ColumnName.FamilyTable.LatestStatus + " INTEGER," +
           ColumnName.FamilyTable.Disease      + " INTEGER," +
           ColumnName.FamilyTable.Father       + " TEXT," +
           ColumnName.FamilyTable.Mother       + " TEXT)";

    private final String CREATE_TABLE_LOG   = "" +
            "CREATE TABLE logTable(" +
            ColumnName.LogTable._ID        +" TEXT," +
            ColumnName.LogTable.OBJECTID   +" TEXT," +
            ColumnName.LogTable.USERID     +" INTEGER," +
            ColumnName.LogTable.MESSAGE    +" TEXT," +
            ColumnName.LogTable.MESSAGECODE+" INTEGER," +
            ColumnName.LogTable.TIMESTAMP  +" TEXT)" ;



}
