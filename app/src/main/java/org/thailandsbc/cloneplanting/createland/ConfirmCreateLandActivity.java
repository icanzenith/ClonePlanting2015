package org.thailandsbc.cloneplanting.createland;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;

public class ConfirmCreateLandActivity extends AppCompatActivity implements OnMapReadyCallback{

    private LandDetailModel landDetail;
    private GoogleMap mMap;
    private BaseApplication b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = (BaseApplication) getApplication();
        setContentView(R.layout.activity_confirm_create_land);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("สรุปข้อมูลแปลง");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    insertToDatabase();
            }
        });
        landDetail = getIntent().getParcelableExtra("LandDetail");
        InitializeViews();
    }

    private void insertToDatabase() {

        ContentValues values = new ContentValues();
        values.put(ColumnName.Land.LandName,landDetail.getLandName());
        values.put(ColumnName.Land.LandArea,landDetail.getLandArea());
        values.put(ColumnName.Land.LandWidth,landDetail.getLandWidth());
        values.put(ColumnName.Land.LandLength,landDetail.getLandLength());
        values.put(ColumnName.Land.Address,landDetail.getAddress());
        values.put(ColumnName.Land.createdTime,b.getTimeUTC());
        values.put(ColumnName.Land.updatedTime,b.getTimeUTC());
        values.put(ColumnName.Land.UserCreate,b.getUserData().getUserID());
        values.put(ColumnName.Land.Latitude,landDetail.getLatitude());
        values.put(ColumnName.Land.Longitude,landDetail.getLongitude());
        values.put(ColumnName.Land.Sector, b.getUserData().getWorkPlaceCode());
        values.put(ColumnName.Land.SugarcaneSelectionType,landDetail.getSugarcaneSelectionType());
        values.put(ColumnName.Land.MaximumRow,getMaximumRow(landDetail));
        values.put(ColumnName.Land.MaximumClonePerFamily, MaximumClonePerFamily);
        values.put(ColumnName.Land.MaximumFamilyPerRow, getMaximumFamilyPerRow(landDetail));

        Uri s = getContentResolver().insert(Database.LAND,values);

        ContentValues values2 = new ContentValues();
        values2.put(ColumnName.Land.LandID, Integer.valueOf(s.getLastPathSegment()));
        String selection = ColumnName.Land.ObjectID+" = "+Integer.valueOf(s.getLastPathSegment());
        Log.d("tag LastPath",selection);
        int rowNumber = getContentResolver().update(Database.LAND, values2, selection, null);
        Log.d("tag clone number","RowNumber"+rowNumber);

        //TODO Finish This an previous Activity
        this.setResult(RESULT_OK);
        finish();
    }

    private void InitializeViews() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView textViewMaximumClonePerRow = (TextView) findViewById(R.id.textViewMaximumClonePerRow);
        TextView textViewMaximumFamilyPerRow = (TextView) findViewById(R.id.textViewMaximumFamilyPerRow);
        TextView textViewMaximumRow = (TextView) findViewById(R.id.textViewMaximumRow);
        TextView textViewAreaSize = (TextView) findViewById(R.id.textViewAreaSize);
        TextView textViewLandDimension = (TextView) findViewById(R.id.textViewLandDimension);
        TextView textViewLandName = (TextView) findViewById(R.id.textViewLandName);


        textViewLandName.setText(landDetail.getLandName());
        textViewAreaSize.setText(String.format("%.2f", landDetail.getLandArea()));
        textViewLandDimension.setText(String.format("%.2f m x %.2f m", landDetail.getLandWidth(), landDetail.getLandLength()));
        textViewMaximumRow.setText(String.format("%d แถว", getMaximumRow(landDetail)));
        textViewMaximumFamilyPerRow.setText(String.format("%d เบอร์", getMaximumFamilyPerRow(landDetail)));
        textViewMaximumClonePerRow.setText(String.format("%d", MaximumClonePerFamily));

    }

    private Integer getMaximumRow(LandDetailModel landDetail) {
        Float maximumRow = 0f;
        Float width = landDetail.getLandWidth();
        maximumRow = width / 1.5f;
        float mod = maximumRow % 1;
        if (mod < 1){
             maximumRow = maximumRow-mod;
        }
        return Integer.valueOf(String.format("%.0f", maximumRow));
    }

    private Integer MaximumClonePerFamily = 35;

    private Integer getMaximumFamilyPerRow(LandDetailModel landDetail) {
        Float result = 0f;
        result = landDetail.getLandLength() / (2 + (MaximumClonePerFamily * 0.6f));
        float mod = result%1;
        if (mod < 1){
            result = result - mod;
        }
        return Integer.valueOf(String.format("%.0f",result));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(landDetail.getLatitude(),landDetail.getLongitude()), 16));

        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_flag))
                .draggable(true)
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(landDetail.getLatitude(),landDetail.getLongitude())));
    }
}