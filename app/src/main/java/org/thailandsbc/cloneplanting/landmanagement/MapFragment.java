package org.thailandsbc.cloneplanting.landmanagement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.utils.DrawPlatedLayout;
import org.thailandsbc.cloneplanting.utils.DrawPolygon;


public class MapFragment extends Fragment implements OnMapReadyCallback{

    public static String AGR_LAND= "Land";

    DrawPolygon drawPolygon;
    LandDetailModel landDetail;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance(LandDetailModel landDetail) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable("Land",landDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            landDetail = getArguments().getParcelable("Land");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        drawPolygon = new DrawPolygon(googleMap);
        drawPolygon.drawSquarePolygon(landDetail);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(landDetail.getLatitude(),landDetail.getLongitude()),2f));
    }
}
