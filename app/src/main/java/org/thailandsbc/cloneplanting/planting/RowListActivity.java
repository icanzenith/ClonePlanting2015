package org.thailandsbc.cloneplanting.planting;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.utils.Land;

import java.util.ArrayList;
import java.util.List;

public class RowListActivity extends AppCompatActivity {

    /**
     * @mTotalFamily
     */
    private Integer mTotalFamily;
    private Integer mTotalClonePlanted;
    private LandDetailModel mLand;


    private RecyclerView recyclerView;
    private static RecyclerView.LayoutManager layoutManager;
    private List<Integer> RowDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("เลือกแถว");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mLand = getIntent().getParcelableExtra(Land.LAND_DETAIL);
        InitializeViews();
    }

    private void InitializeViews() {


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        CardView container = (CardView) findViewById(R.id.container);

        TextView textViewTotalClone = (TextView) findViewById(R.id.textViewTotalClone);
        TextView textViewTotalFamily = (TextView) findViewById(R.id.textViewTotalFamily);
        TextView textViewRowAmount = (TextView) findViewById(R.id.textViewRowAmount);
        TextView textViewLandName = (TextView) findViewById(R.id.textViewLandName);

        createRecyclerView();

        textViewLandName.setText(mLand.getLandName().toString());
        textViewTotalClone.setText("" + countClonePlanted());
        textViewRowAmount.setText(""+getRowNumber());
        textViewTotalFamily.setText("" + countPlantedFamily());

    }

    private void createRecyclerView() {
        getCurrentLandData();
        layoutManager = new LinearLayoutManager(this);
        RowListAdapter mAdapter = new RowListAdapter(RowDataSet, this, mLand);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


    }

    private void getCurrentLandData() {
        RowDataSet = new ArrayList<>();
        Integer mMaximumRow = 0;


        Uri uri = Database.PLANTEDCLONE;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor c = getContentResolver().query(uri, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
            }
        }
        mTotalFamily = c.getCount();
        c.close();


        //Count Clone
        selection = ColumnName.PlantedClone.LandID + " = " + mLand.getLandID();
        c = getContentResolver().query(uri, null, selection, null, null);
        if (c != null) {
            mTotalClonePlanted = c.getCount();
        } else {
            mTotalClonePlanted = 0;
        }
        c.close();

        //get row Number
        getRowNumber();
        countPlantedFamily();


    }
    private Integer countClonePlanted(){
        Integer cloneAmount = 0;
        String selection = ColumnName.PlantedClone.LandID + " = " + mLand.getLandID();
        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, null, selection, null, null);
        if (c != null) {
            while (c.moveToNext()){
                cloneAmount = cloneAmount+c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.PlantedAmount));
            }
        } else {
            mTotalClonePlanted = 0;
        }
        c.close();
        return cloneAmount;
    }

    private Integer getRowNumber(){
        Integer mMaximumRow = 0;
        String selection = ColumnName.Land.LandID + " = " + mLand.getLandID();

        Cursor c = getContentResolver().query(Database.LAND, null, selection, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                mMaximumRow = c.getInt(c.getColumnIndex(ColumnName.Land.MaximumRow));
            }
        } else {
            mMaximumRow = 0;
        }
        for (int i = 0; i < mMaximumRow; i++) {
            RowDataSet.add(i + 1);
        }
        c.close();

        return mMaximumRow;
    }

    private Integer countPlantedFamily() {
        String selection = ColumnName.ReceivedClone.LandID+" = "+mLand.getLandID();
        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, null, selection, null, null);
        return c.getCount();
    }
}
