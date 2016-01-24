package org.thailandsbc.cloneplanting.database;

import android.net.Uri;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */
public class Database {
    public static final String SCHEME = "content://";

    public static final String AUTHORITY = "ThailandSugarcaneBreedingCenter";
    public static final String TABLE_FAMILY = "familyTable";
    public static final String TABLE_LOG    = "logTable";
    public static final String TABLE_SECTOR           =   "Sector";
    public static final String TABLE_SENTCLONE        =   "SentClone";
    public static final String TABLE_RECEIVEDCLONE    =   "ReceivedClone";
    public static final String TABLE_PLANTEDCLONE     =   "PlantedClone";
    public static final String TABLE_PLANTEDFAMILY     =   "PlantedFamily";
    public static final String TABLE_LAND             =   "Land";
    public static final String TABLE_ACTIVITY         =   "Activity";
    public static final String TABLE_COMMENT          =   "Comment";
    public static final String TABLE_PICTURE          =   "Picture";

    public static final Uri Family             =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_FAMILY       );
    public static final Uri Log                =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_LOG          );
    public static final Uri SECTOR             =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_SECTOR       );
    public static final Uri SENTCLONE          =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_SENTCLONE    );
    public static final Uri RECEIVEDCLONE      =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_RECEIVEDCLONE);
    public static final Uri PLANTEDFAMILY      =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_PLANTEDFAMILY);
    public static final Uri PLANTEDCLONE       =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_PLANTEDCLONE );
    public static final Uri LAND               =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_LAND         );
    public static final Uri ACTIVITY           =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_ACTIVITY     );
    public static final Uri COMMENT            =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_COMMENT      );
    public static final Uri PICTURE            =      Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_PICTURE      );



}
