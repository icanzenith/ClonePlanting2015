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
    public static final Uri Family =Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_FAMILY);
    public static final Uri Log =Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_LOG);

}
