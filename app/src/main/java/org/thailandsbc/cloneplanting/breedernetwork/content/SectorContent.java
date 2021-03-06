package org.thailandsbc.cloneplanting.breedernetwork.content;

import android.content.Context;

import com.androidquery.AQuery;

import org.thailandsbc.cloneplanting.utils.WorkPlaceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectorContent {

    private AQuery aq;
    private Context context;

    public SectorContent(Context context, ArrayList<SectorItem> data) {

        this.context = context;
        aq = new AQuery(context);
//        COUNT = data.size();
//        for (int i = 0; i<data.size();i++){
//            createBreederItem(data.get(i));
//        }
    }

    public  ArrayList<SectorItem> CreateSampleSectorArrayData(){
        String[] symbol = new String[]{"A", "B", "C", "D", "E", "G", "H", "I", "J", "K", "L", "M"};
        for(int i = 0; i < symbol.length ;i++){
            String Place = WorkPlaceData.PLACE_CODE.get(symbol[i]);
            SectorItem item = new SectorItem(symbol[i], Place);
            createSectorItem(item);
        }
        return (ArrayList<SectorItem>) ITEMS;
    }

    private int COUNT = 0;

    public void createSectorItem(SectorItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.SectorCode,item);
    }


    public final List<SectorItem> ITEMS = new ArrayList<>();

    public final Map<String, SectorItem> ITEM_MAP = new HashMap<>();


    public static class SectorItem {
        public final String SectorCode;
        public final String FullName;


        public SectorItem(String sectorCode, String name) {
            this.SectorCode = sectorCode;
            this.FullName = name;

        }

        @Override
        public String toString() {
            return " URL : "+
                    SectorCode +" Name : "+ FullName;
        }
    }
}
