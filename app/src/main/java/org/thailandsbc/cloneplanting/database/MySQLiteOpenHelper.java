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

    private static final String TEXT = " TEXT,";
    private static final String INTEGER = " INTEGER,";
    private static final String REAL = " REAL,";

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
        db.execSQL(CREATE_TABLE_LOG             );
        db.execSQL(CREATE_TABLE_SECTOR      );
        db.execSQL(CREATE_TABLE_SENTCLONE       );
        db.execSQL(CREATE_TABLE_RECEIVEDCLONE       );
        db.execSQL(CREATE_TABLE_PLANTEDCLONE        );
        db.execSQL(CREATE_TABLE_LAND        );
        db.execSQL(CREATE_TABLE_ACTIVITY        );
        db.execSQL(CREATE_TABLE_COMMENT     );
        db.execSQL(CREATE_TABLE_PICTURE     )    ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS familyTable");
        db.execSQL("DROP TABLE IF EXISTS logTable");
        db.execSQL("DROP TABLE IF EXISTS Sector");
        db.execSQL("DROP TABLE IF EXISTS SentClone");
        db.execSQL("DROP TABLE IF EXISTS ReceivedClone");
        db.execSQL("DROP TABLE IF EXISTS PlantedClone");
        db.execSQL("DROP TABLE IF EXISTS Land");
        db.execSQL("DROP TABLE IF EXISTS Comment");
        db.execSQL("DROP TABLE IF EXISTS Picture");
        db.execSQL("DROP TABLE IF EXISTS Activity");
        onCreate(db);

    }

    private final String CREATE_TABLE_FAMILY = "" +
            "CREATE TABLE familyTable(" +
            ColumnName.FamilyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ColumnName.FamilyTable.FamilyCode + " TEXT," +
            ColumnName.FamilyTable.objectId + " INTEGER," +
            ColumnName.FamilyTable.SendAmount + " INTEGER," +
            ColumnName.FamilyTable.SenderID + " TEXT," +
            ColumnName.FamilyTable.SendTo + " TEXT," +
            ColumnName.FamilyTable.ReceiveAmount + " INTEGER," +
            ColumnName.FamilyTable.ReceiverID + " INTEGER," +
            ColumnName.FamilyTable.PlantAmount + " INTEGER," +
            ColumnName.FamilyTable.PlanterID + " INTEGER," +
            ColumnName.FamilyTable.LeftAmount + " INTEGER," +
            ColumnName.FamilyTable.LeftCheckerID + " INTEGER," +
            ColumnName.FamilyTable.RowNumber + " INTEGER," +
            ColumnName.FamilyTable.OrderInRow + " INTEGER," +
            ColumnName.FamilyTable.LandID + " TEXT," +
            ColumnName.FamilyTable.LatestStatus + " INTEGER," +
            ColumnName.FamilyTable.Disease + " INTEGER," +
            ColumnName.FamilyTable.Father + " TEXT," +
            ColumnName.FamilyTable.Mother + " TEXT)";

    private final String CREATE_TABLE_LOG = "" +
            "CREATE TABLE logTable(" +
            ColumnName.LogTable._ID + " TEXT," +
            ColumnName.LogTable.OBJECTID + " TEXT," +
            ColumnName.LogTable.USERID + " INTEGER," +
            ColumnName.LogTable.MESSAGE + " TEXT," +
            ColumnName.LogTable.MESSAGECODE + " INTEGER," +
            ColumnName.LogTable.TIMESTAMP + " TEXT)";


    private final String CREATE_TABLE_SECTOR = "" +
            "CREATE TABLE Sector(" +
            ColumnName.Sector.CODE + " TEXT PRIMARY KEY," +
            ColumnName.Sector.FullSectorName + " TEXT)";

    private final String CREATE_TABLE_SENTCLONE = "" +
            "CREATE TABLE SentClone(" +
            ColumnName.SentClone.NameTent + " TEXT," +
            ColumnName.SentClone.SentBy + " TEXT," +
            ColumnName.SentClone.SentTo + " TEXT," +
            ColumnName.SentClone.SentAmount + " INTEGER," +
            ColumnName.SentClone.UserSender + " INTEGER," +
            ColumnName.SentClone.createdTime + " TEXT," +
            ColumnName.SentClone.updatedTime + " TEXT," +
            ColumnName.SentClone.MotherCode + " TEXT," +
            ColumnName.SentClone.FatherCode + " TEXT)";


    //ข้อมูลการรับ เป็น Family และ ข้อมูลตำแหน่งที่ปลูก
    private final String CREATE_TABLE_RECEIVEDCLONE = "" +
            "CREATE TABLE ReceivedClone(" +
            ColumnName.ReceivedClone.ObjectID + " INTEGER PRIMARY KEY," +
            ColumnName.ReceivedClone.NameTent + TEXT +
            ColumnName.ReceivedClone.SentBy + TEXT +
            ColumnName.ReceivedClone.ReceivedBy + TEXT +
            ColumnName.ReceivedClone.UserReceiver + TEXT +
            ColumnName.ReceivedClone.ReceivedAmount + TEXT +
            ColumnName.ReceivedClone.createdTime + TEXT +
            ColumnName.ReceivedClone.updatedTime + TEXT +
            ColumnName.ReceivedClone.MotherCode + TEXT +
            ColumnName.ReceivedClone.FatherCode + TEXT +
            ColumnName.ReceivedClone.isPlanted + INTEGER +
            ColumnName.ReceivedClone.PlantedBy + TEXT +
            ColumnName.ReceivedClone.RowNumber + INTEGER +
            ColumnName.ReceivedClone.OrderInRow + INTEGER +
            ColumnName.ReceivedClone.PlantedTime + TEXT +
            ColumnName.ReceivedClone.PlantedAmount + INTEGER+
            ColumnName.ReceivedClone.SurviveAmount + INTEGER+
            ColumnName.ReceivedClone.LandID + " INTEGER, " +
            "FOREIGN KEY("+ColumnName.ReceivedClone.LandID+") REFERENCES Land("+ColumnName.Activity.LandID+"))";

    private final String CREATE_TABLE_PLANTEDCLONE = "" +
            "CREATE TABLE PlantedClone(" +
            ColumnName.PlantedClone.ObjectID+INTEGER+
            ColumnName.PlantedClone.CloneCode+TEXT+
            //is Dead = 1 แสดงว่า ตาย
            ColumnName.PlantedClone.isDead+INTEGER+
            ColumnName.PlantedClone.createdTime+TEXT+
            ColumnName.PlantedClone.updatedTime+TEXT+
            ColumnName.PlantedClone.NameTent +TEXT+
            ColumnName.PlantedClone.LandID+
            " INTEGER)";

    private final String CREATE_TABLE_LAND = "" +
            "CREATE TABLE Land(" +
            ColumnName.Land.ObjectID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ColumnName.Land.LandID+INTEGER+
            ColumnName.Land.LandName+TEXT+
            ColumnName.Land.LandLength+REAL+
            ColumnName.Land.LandWidth+REAL+
            ColumnName.Land.LandArea+REAL+
            ColumnName.Land.UserCreate+INTEGER+
            ColumnName.Land.Address+TEXT+
            ColumnName.Land.Sector+TEXT+
            ColumnName.Land.Latitude+REAL+
            ColumnName.Land.Longitude+REAL+
            ColumnName.Land.YearCrossing+TEXT+
            ColumnName.Land.SugarcaneSelectionType+INTEGER+
            ColumnName.Land.createdTime+TEXT+
            ColumnName.Land.updatedTime+TEXT+
            ColumnName.Land.MaximumRow+INTEGER+
            ColumnName.Land.MaximumFamilyPerRow+INTEGER+
            ColumnName.Land.MaximumClonePerFamily+
            " INTEGER)";

    private final String CREATE_TABLE_ACTIVITY = "" +
            "CREATE TABLE Activity(" +
            ColumnName.Activity.ObjectID+INTEGER+
            ColumnName.Activity.ActivityID+INTEGER+
            ColumnName.Activity.UserID+INTEGER+
            ColumnName.Activity.ActivityType+INTEGER+
            ColumnName.Activity.Sector+TEXT+
            ColumnName.Activity.createdTime+TEXT+
            ColumnName.Activity.updatedTime+TEXT+
            ColumnName.Activity.LandID+INTEGER+
            ColumnName.Activity.Message+" TEXT,"+
            "FOREIGN KEY("+ColumnName.Activity.LandID+") REFERENCES Land("+ColumnName.Activity.LandID+
            "))";

    private final String CREATE_TABLE_COMMENT ="" +
            "CREATE TABLE Comment(" +
            ColumnName.Comment.ObjectID   +INTEGER+
            ColumnName.Comment.CommentID  +INTEGER+
            ColumnName.Comment.ActivityID+INTEGER+
            ColumnName.Comment.UserID     +INTEGER+
            ColumnName.Comment.Message    +TEXT+
            ColumnName.Comment.createdTime+TEXT+
            ColumnName.Comment.updatedTime+" TEXT,"+
            "FOREIGN KEY("+ColumnName.Comment.ActivityID+") REFERENCES Activity("+ColumnName.Comment.ActivityID+
            "))";

    private final String CREATE_TABLE_PICTURE = "" +
            "CREATE TABLE Picture(" +
            ColumnName.Picture.ObjectID   +INTEGER+
            ColumnName.Picture.PictureID  +INTEGER+
            ColumnName.Picture.ActivityID +INTEGER+
            ColumnName.Picture.PictureURL +TEXT+
            ColumnName.Picture.createdTime+TEXT+
            ColumnName.Picture.updatedTime+" TEXT,"+
            "FOREIGN KEY("+ColumnName.Picture.ActivityID+") REFERENCES Activity("+ColumnName.Picture.ActivityID+
            "))";


}
