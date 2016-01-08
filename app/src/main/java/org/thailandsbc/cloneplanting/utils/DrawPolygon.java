package org.thailandsbc.cloneplanting.utils;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.thailandsbc.cloneplanting.model.LandDetailModel;

/**
 * Created by Icanzenith on 1/8/2016 AD.
 */
public class DrawPolygon {
    private GoogleMap mMap;

    public DrawPolygon(GoogleMap map) {
        this.mMap = map;

    }

    public Polygon drawSquarePolygon(LandDetailModel land){
        LatLng[] latLngs = getSquareLatLng(land);
        Polygon polygon = mMap.addPolygon(new PolygonOptions().add(latLngs).
                strokeColor(Color.RED)
                .fillColor(Color.BLUE));


        return polygon;
    }
    static double GPS_Constant = 0.00001;
    private LatLng[] getSquareLatLng(LandDetailModel land){
        LatLng[] latLngs = new LatLng[4];
        LatLng centerLatLng = new LatLng(land.getLatitude(),land.getLongitude());
        double width = land.getLandWidth()* GPS_Constant;
        double length = land.getLandLength()* GPS_Constant;


        latLngs[0] = new LatLng(centerLatLng.latitude-(width/2),centerLatLng.longitude+(length/2));
        latLngs[1] = new LatLng(centerLatLng.latitude+(width/2),centerLatLng.longitude+(length/2));
        latLngs[2] = new LatLng(centerLatLng.latitude+(width/2),centerLatLng.longitude-(length/2));
        latLngs[3] = new LatLng(centerLatLng.latitude-(width/2),centerLatLng.longitude-(length/2));


        return latLngs;
    }
}
