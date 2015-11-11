package org.thailandsbc.cloneplanting.createland;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class LandSelectionActivity extends AppCompatActivity implements onFragmentInteractionListener{

    FloatingActionButton fab;
    Toolbar toolbar;
    private String Selection_Mode;
    LandListAdapter mAdapter;
    RecyclerView recyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_selection);

        Selection_Mode = getIntent().getStringExtra(SelectionMode.MODE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("เลือกแปลง");
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LandSelectionActivity.this,CreateLandActivity.class);
                startActivity(intent);

            }
        });
        InitializeViews();

    }

    private void InitializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }

    private void createLandList() {
        List<LandDetailModel> dataSet = createDataSet();
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LandListAdapter(this,dataSet,Selection_Mode);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        createLandList();
    }

    private List<LandDetailModel> createDataSet() {
        List<LandDetailModel> data = new ArrayList<>();
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.LAND, projection, selection, selectionArgs, sortOrder);
        if (c.getCount()!=0){
            Log.d("TAG",""+c.getCount());
            while (c.moveToNext()){
                LandDetailModel m = new LandDetailModel();
                m.setLandID(c.getInt(c.getColumnIndex(ColumnName.Land.LandID)));
                Log.d("TAG LANDID",m.getLandID()+"");
                m.setLandName(c.getString(c.getColumnIndex(ColumnName.Land.LandName)));
                m.setLatitude(c.getDouble(c.getColumnIndex(ColumnName.Land.Latitude)));
                m.setLongitude(c.getDouble(c.getColumnIndex(ColumnName.Land.Longitude)));
                m.setLandArea(c.getFloat(c.getColumnIndex(ColumnName.Land.LandArea)));
                m.setLandWidth(c.getFloat(c.getColumnIndex(ColumnName.Land.LandWidth)));
                m.setLandLength(c.getFloat(c.getColumnIndex(ColumnName.Land.LandLength)));
                m.setUserCreate(c.getInt(c.getColumnIndex(ColumnName.Land.UserCreate)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.Land.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.Land.updatedTime)));
                m.setSugarcaneSelectionType(c.getInt(c.getColumnIndex(ColumnName.Land.SugarcaneSelectionType)));
                m.setYearCrossing(c.getString(c.getColumnIndex(ColumnName.Land.YearCrossing)));
                m.setAddress(c.getString(c.getColumnIndex(ColumnName.Land.Address)));
                data.add(m);
            }
        }
        return data;
    }

    @Override
    public void onFragmentInteraction(String TAG, Object object) {

    }
}
