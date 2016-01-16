package org.thailandsbc.cloneplanting.model;

import java.util.ArrayList;

/**
 * Created by Icanzenith on 1/16/2016 AD.
 */
public class ActivityData {
    public ArrayList<PostData> data = new ArrayList<>();
    public class PostData {
        public int postId;
        public From from;
        public ArrayList<Liker> liker;
        public ArrayList<Picture> picture;
        public ArrayList<Comment> comments;
        public String createdTime;
        public String updatedTime;

        class From {
            public String name;
            public int userId;
            public String pictureURL;
            public String message;
        }

        class Liker {
            public String name;
            public int id;
            public String pictureURL;
        }

        class Picture {
            public String url;
            public int picture;
        }

        class Comment {
            public int id;
            public String author;
            public String message;
        }
    }
}