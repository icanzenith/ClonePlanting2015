package org.thailandsbc.cloneplanting.breedernetwork.content;

import android.content.Context;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreederContent {

    private AQuery aq;

    public BreederContent(Context context,ArrayList<BreederItem> data) {
        aq = new AQuery(context);
//        COUNT = data.size();
//        for (int i = 0; i<data.size();i++){
//            createBreederItem(data.get(i));
//        }

    }

    public  ArrayList<BreederItem> CreateSampleBreederArrayData(){
    String[] name = {"Jitpakorn Rattanadilok na buket","Somwang Leekhar","Borpit Tangwongkit","Rattana Tangwongkit","Wannapa Kamoljinda","Krongsapz Sangthong","Sunnattha Autthawisetsin",
    "Todsaphon Chowiset","Pathompong Kumpul","Wanwanat Chainarong","Nothawat Chainarong","Kamol Muantea","Sakol Todamol","John Beacon","Hello Andromeda","Anakin Skywalker","Johny Anwa",
    "Annan Anwa","Labanoon Ontherock","Cookie butnorun"};


        for(int i = 0; i < name.length ;i++){
            int random = (int) (Math.random()*10);
            String url = "http://lorempixel.com/400/200/people/"+random;
            BreederItem item = new BreederItem(String.valueOf(i),url,name[i],i);
            createBreederItem(item);
        }
        return (ArrayList<BreederItem>) ITEMS;
    }

    private int COUNT = 0;

    public void createBreederItem(BreederItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.id,item);
    }

    /**
     * An array of sample (dummy) items.
     */
    public final List<BreederItem> ITEMS = new ArrayList<BreederItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public final Map<String, BreederItem> ITEM_MAP = new HashMap<String, BreederItem>();


    public static class BreederItem {
        public final String id;
        public final String PictureURL;
        public final String Name;
        public final int UserID;

        public BreederItem(String id, String profileURL, String name, int userID) {
            this.id = id;
            this.PictureURL = profileURL;
            this.Name = name;
            this.UserID = userID;
        }

        @Override
        public String toString() {
            return " URL : "+PictureURL+" Name : "+Name+" UserID : "+UserID;
        }
    }
}
