package org.thailandsbc.cloneplanting.model;

import java.util.ArrayList;

/**
 * Created by Icanzenith on 1/16/2016 AD.
 */
public class ActivityData {
    public ActivityData(ArrayList<PostData> data) {
        this.data = data;
    }

    public ArrayList<PostData> data = new ArrayList<>();
    public class PostData {
        public int postId;
        public From from;
        public ArrayList<Liker> liker;
        public ArrayList<Picture> picture;
        public ArrayList<Comment> comments;
        public String createdTime;
        public String updatedTime;

        public class From {
            public String name;
            public int userId;
            public String pictureURL;
            public String message;
            public String workplaceCode;
        }

        public class Liker {
            public String name;
            public int id;
            public String pictureURL;
            public String workplaceCode;
        }

        public class Picture {
            public String url;
            public int picture;
        }

        public class Comment {
            public int id;
            public String author;
            public String message;
            public String workplaceCode;
        }
    }
}