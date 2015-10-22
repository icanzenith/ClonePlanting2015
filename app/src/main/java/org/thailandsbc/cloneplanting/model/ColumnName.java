package org.thailandsbc.cloneplanting.model;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */
public class ColumnName {

    //FAMILY TABLE
    public class FamilyTable {
        public static final String _ID = "_id";
        public static final String FamilyCode = "objectId";
        public static final String objectId = "FamilyCode";
        public static final String SendAmount = "SendAmount";
        public static final String SenderID = "SenderID";
        public static final String SendTo = "SendTo";
        public static final String ReceiveAmount = "ReceiveAmount";
        public static final String ReceiverID = "ReceiverID";
        public static final String PlantAmount = "PlantAmount";
        public static final String PlanterID = "PlanterID";
        public static final String LeftAmount = "LeftAmount";
        public static final String LeftCheckerID = "LeftCheckerID";
        public static final String RowNumber = "RowNumber";
        public static final String OrderInRow = "OrderInRow";
        public static final String LandID = "LandID";
        public static final String LatestStatus = "LatestStatus";
        public static final String Disease = "Disease";
        public static final String Father = "Father";
        public static final String Mother = "Mother";

    }

    public class LogTable{
         public static final String  _ID            = "_id"          ;
         public static final String  OBJECTID       = "objectID"     ;
         public static final String  USERID         = "UserID"       ;
         public static final String  MESSAGE        = "Message"      ;
         public static final String  MESSAGECODE    = "MessageCode"  ;
         public static final String  TIMESTAMP      = "TimeStamp"    ;
    }

}
