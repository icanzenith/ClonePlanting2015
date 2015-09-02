package org.thailandsbc.cloneplanting.model;

import java.sql.Timestamp;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */
public class NewsFeedModel {

    private int ActivityID;
    private int UserID;
    private String UserFullName;
    private String WorkPlace;
    private String WorkPlaceCode;

    class PostDetail{
        String TextMessage;
        String PictureURL;
        String TimpStamp;
    }

    private boolean LikeStatus;

    class Location{
        private long Latitude;
        private long Longitude;
    }
}
