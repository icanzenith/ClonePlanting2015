package org.thailandsbc.cloneplanting.createland;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.SugarcaneSelectionType;
import org.thailandsbc.cloneplanting.model.LandDetailModel;

public class CreateLandActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, View.OnClickListener,
        LocationListener,GoogleApiClient.ConnectionCallbacks,GoogleMap.OnMyLocationChangeListener {

    LandDetailModel mLandDetail;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    EditText editTextLandArea;
    EditText editTextLandLength;
    EditText editTextLandAddress;
    EditText editTextLandWidth;
    EditText editTextLandName;
    Spinner spinnerSelectionType;

    private int mSelectionType;

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private FloatingActionButton fabDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_land);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("สร้างแปลง");
        setSupportActionBar(toolbar);

        initializeViews();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            finish();
        }
    }

    private void initializeViews() {

        fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        fabDone.setOnClickListener(this);


        editTextLandWidth = (EditText) findViewById(R.id.editTextLandWidth);
        editTextLandArea = (EditText) findViewById(R.id.editTextLandArea);
        editTextLandAddress = (EditText) findViewById(R.id.editTextLandAddress);
        editTextLandLength = (EditText) findViewById(R.id.editTextLandLength);
        spinnerSelectionType = (Spinner) findViewById(R.id.spinnerSelectionType);
        editTextLandName = (EditText) findViewById(R.id.editTextLandName);

        spinnerSelectionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("1st Selection")) {
                    mSelectionType = SugarcaneSelectionType.FIRST_SELECTION;
                }
                if (parent.getItemAtPosition(position).equals("2nd Selection")) {
                    mSelectionType = SugarcaneSelectionType.SECOND_SELECTION;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(this);
            onMyLocationButtonClick();

        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    private LandDetailModel setLandDetail() {
        LandDetailModel d = new LandDetailModel();
        d.setLandName(editTextLandAddress.getText().toString());
        d.setLatitude(mMap.getMyLocation().getLatitude());
        d.setLongitude(mMap.getMyLocation().getLongitude());
        d.setAddress(editTextLandAddress.getText().toString());
        d.setLandArea(Float.valueOf(editTextLandArea.getText().toString()));
        d.setLandWidth(Float.valueOf(editTextLandWidth.getText().toString()));
        d.setLandLength(Float.valueOf(editTextLandLength.getText().toString()));
        d.setSugarcaneSelectionType(mSelectionType);
        return d;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabDone) {
            mLandDetail = setLandDetail();
            String TAG = "TAG Land Detail";
            Log.d(TAG, mLandDetail.getLatitude() + "");
            Log.d(TAG, mLandDetail.getLongitude() + "");
            Log.d(TAG, mLandDetail.getSugarcaneSelectionType() + "");

            Intent intent = new Intent(CreateLandActivity.this,ConfirmCreateLandActivity.class);
            intent.putExtra("LandDetail",mLandDetail);
            startActivityForResult(intent,0);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLandDetail.setLatitude(location.getLatitude());
        mLandDetail.setLongitude(location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMyLocationChange(Location location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(),
                        location.getLongitude()),
                16));

    }
}
