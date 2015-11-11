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

    public class Sector{
        public static final String CODE = "Code";
        public static final String FullSectorName = "FullSectorName";
    }

    public class SentClone{
        public static final String ObjectID="ObjectID";
        public static final String FamilyCode = "FamilyCode";
        public static final String SentBy = "SentBy";
        public static final String SentTo = "SentTo";
        public static final String SentAmount = "SentAmount";
        public static final String UserSender = "UserSender";
        public static final String createdTime = "createdTime";
        public static final String updatedTime = "updatedTime";
        public static final String MotherCode = "MotherCode";
        public static final String FatherCode = "FatherCode";
    }

    public class ReceivedClone{
        public static final String ObjectID         ="ObjectID";
        public static final String FamilyCode       = "FamilyCode";
        public static final String SentBy           ="SentBy";
        public static final String ReceivedBy       = "ReceivedBy";
        public static final String UserReceiver      = "UserReceiver";
        public static final String ReceivedAmount   = "ReceivedAmount";
        public static final String createdTime      = "createdTime";
        public static final String updatedTime      = "updatedTime";
        public static final String MotherCode       = "MotherCode";
        public static final String FatherCode       = "FatherCode";
        public static final String isPlanted        = "isPlanted";
        public static final String PlantedBy        = "PlantedBy";
        public static final String RowNumber        = "RowNumber";
        public static final String OrderInRow       = "OrderInRow";
        public static final String PlantedTime      = "PlantedTime";
        public static final String LandID           = "LandID";
        public static final String PlantedAmount    = "PlantedAmount";
    }

    public class PlantedClone{
        public static final String ObjectID="ObjectID";
        public static final String CloneCode = "CloneCode";
        public static final String isDead = "isDead";
        public static final String createdTime = "createdTime";
        public static final String updatedTime = "updatedTime";
        public static final String FamilyCode = "FamilyCode";
        public static final String LandID = "LandID";

    }

    public class Land{
        public static final String ObjectID="ObjectID";
        public static final String LandID = "LandID";
        public static final String LandName = "LandName";
        public static final String LandLength = "LandLength";
        public static final String LandWidth = "LandWidth";
        public static final String LandArea = "LandArea";
        public static final String UserCreate = "UserCreate";
        public static final String Address ="Address";
        public static final String Sector = "Sector";
        public static final String Latitude ="Latitude";
        public static final String Longitude = "Longitude";
        public static final String YearCrossing = "YearCrossing";
        public static final String SugarcaneSelectionType = "SugarcaneSelectionType";
        public static final String createdTime = "createdTime";
        public static final String updatedTime = "updatedTime";
        //TODO เพิ่ม
        public static final String MaximumRow = "MaximumRow";
        public static final String MaximumFamilyPerRow = "MaximumFamilyPerRow";
        public static final String MaximumClonePerFamily = "MaximumClonePerFamily";
    }

    public class Activity{
        public static final String ObjectID="ObjectID";
        public static final String ActivityID = "ActivityID";
        public static final String UserID = "UserID";
        public static final String ActivityType = "ActivityType";
        public static final String Sector = "Sector";
        public static final String createdTime = "createdTime";
        public static final String updatedTime ="updateTime";
        public static final String LandID ="LandID";
        public static final String Message="Message";
    }

    public class Comment{
        public static final String ObjectID     ="ObjectID";
        public static final String CommentID    = "CommentID";
        public static final String ActivityID   = "ActivityID";
        public static final String UserID       = "UserID";
        public static final String Message      = "Message";
        public static final String createdTime  = "createdTime";
        public static final String updatedTime  = "updatedTime";
    }

    public class Picture{
        public static final String ObjectID     ="ObjectID";
        public static final String PictureID    = "PictureID";
        public static final String ActivityID   = "ActivityID";
        public static final String PictureURL   = "PictureURL";
        public static final String createdTime  = "createdTime";
        public static final String updatedTime  = "updatedTime";

    }




}
