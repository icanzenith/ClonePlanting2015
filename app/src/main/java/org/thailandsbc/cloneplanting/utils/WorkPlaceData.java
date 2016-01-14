package org.thailandsbc.cloneplanting.utils;

import org.thailandsbc.cloneplanting.breedernetwork.content.SectorContent;

import java.util.HashMap;

/**
 * Created by Icanzenith on 1/13/2016 AD.
 */
public class WorkPlaceData {

    public static HashMap<String,String> PLACE_CODE = new HashMap<>();

    static {
            createMapCode();
    }

    public static void createMapCode(){
        PLACE_CODE.put("A","Kasetsart University");
        PLACE_CODE.put("B","Sector B");
        PLACE_CODE.put("C","Sector C");
        PLACE_CODE.put("D","Sector D");
        PLACE_CODE.put("E","Sector E");

        PLACE_CODE.put("G","Sector G");
        PLACE_CODE.put("H","Sector H");
        PLACE_CODE.put("I","Sector I");
        PLACE_CODE.put("J","Sector J");
        PLACE_CODE.put("K","Sector K");
        PLACE_CODE.put("L","Sector L");
        PLACE_CODE.put("M","Sector M");
    }


}
