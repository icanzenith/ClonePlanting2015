package org.thailandsbc.cloneplanting.utils;


import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.thailandsbc.cloneplanting.model.LandDetailModel;

public class DrawPlatedLayout {

    private static final double CLONE_MARGIN_LENGTH = 0.6;
    private GoogleMap map;
    private LandDetailModel land;

    private int rowAmount = 0;
    private int familyPerRow = 0;

    private static double GPS_CONSTANT = 0.00001;
    private double rowMargin = 1.5;
    private double rowMarginConstant = 0;
    private double rowMarginXConstant = 0;
    private double rowMarginYConstant = 0;
    //ความยาวของแต่ละแถว Family
    private double familyRowLength = 0;
    private double marginBetweenFamily = 2*GPS_CONSTANT;
    private String TAG = "DrawPlantedLayout";

    public DrawPlatedLayout(GoogleMap map, LandDetailModel land) {
        this.map = map;
        this.land = land;
        rowMarginConstant = rowMargin * GPS_CONSTANT;

        // Lat or X position is same in the each row;

        rowMarginXConstant = rowMarginConstant;
        rowMarginYConstant = rowMarginConstant;
        rowAmount = land.getMaximumRow();
        familyPerRow = land.getMaximumFamilyPerRow();
        Log.d(TAG, "createLatLngsData: familyPerRow 1"+familyPerRow);
        familyRowLength = land.getMaximumClonePerFamily()*CLONE_MARGIN_LENGTH*GPS_CONSTANT;

    }

    public void startDrawMap() {
            createLatLngsData();


    }

    private void createLatLngsData() {

        double width = land.getLandWidth()*GPS_CONSTANT;
        double length = land.getLandLength()*GPS_CONSTANT;

        LatLng centerLatLng = new LatLng(land.getLatitude(), land.getLongitude());
        LatLng startCornerLatLng = new LatLng(centerLatLng.latitude + (width / 2), centerLatLng.longitude - (length / 2));
        Log.d(TAG, "createLatLngsData: LatLng Start Corner"+startCornerLatLng);

        LatLng lastStartLatLng = null;
        LatLng lastEndLatLng = null;
        Log.d(TAG, "createLatLngsData: Row Amount " + rowAmount);
        for (int i = 0 ; i < rowAmount ;i++){

            LatLng lastLatLngInRow = null;
            int currentRow = i+1;

            for (int j = 1; j<(familyPerRow+1) ;j++){
                LatLng startLatLng;
                LatLng endLatLng ;
                Log.d(TAG, "createLatLngsData: familyPerRow"+familyPerRow);
                //First Order of Row
                if (j==1){
                    //First Position
                    startLatLng = new LatLng(
                            startCornerLatLng.latitude-(currentRow*rowMarginYConstant),
                            startCornerLatLng.longitude+rowMarginXConstant);

                    endLatLng = new LatLng(
                            startLatLng.latitude,
                            startCornerLatLng.longitude+familyRowLength);
//                    lastLatLngInRow = endLatLng;

                    startDrawPolyline(startLatLng,endLatLng);

                    Log.d(TAG, "createLatLngsData: Lat"+startLatLng +" , Lnt"+endLatLng+" R: "+i+" O:"+j);
                    lastStartLatLng = startLatLng;
                    lastEndLatLng = endLatLng;
                }else{

                    startLatLng = new LatLng(
                            lastEndLatLng.latitude,
                            lastEndLatLng.longitude+marginBetweenFamily);
                    endLatLng = new LatLng(
                            startLatLng.latitude,
                            startLatLng.longitude+familyRowLength);

                    startDrawPolyline(startLatLng,endLatLng);
                    lastStartLatLng = startLatLng;
                    lastEndLatLng = endLatLng;
                }

            }

        }

    }

    private void startDrawPolyline(LatLng startLatLng, LatLng endLatLng) {
        map.addPolyline(new PolylineOptions().add(startLatLng,endLatLng).width(5).color(Color.RED));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,10f));


    }


}
