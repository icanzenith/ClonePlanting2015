package org.thailandsbc.cloneplanting.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.customview.CustomViewExampleActivity;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;

public class TestActivity extends AppCompatActivity {

    private Button buttonGenerateLandData;
    private Button buttonDeleteAllLandData;
    private Button buttonOpenCustomView;
    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        baseApplication = (BaseApplication) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buttonGenerateLandData = (Button) findViewById(R.id.buttonGenerateLandData);
        buttonGenerateLandData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSampleLandData();
            }
        });

        buttonDeleteAllLandData  = (Button) findViewById(R.id.buttonDeleteAllLandData);
        buttonDeleteAllLandData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {DeleteSampleLandData();
            }
        });

        buttonOpenCustomView = (Button) findViewById(R.id.buttonCustomViewExample);
        buttonOpenCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, CustomViewExampleActivity.class);
                startActivity(intent);
            }
        });
    }


    private void AddSampleLandData(){
        //TODO Test Add Event แปลง
        ContentResolver cr = getContentResolver();
        for (int i  = 0;i<10;i++){
            ContentValues v = new ContentValues();
            v.put(ColumnName.Land.LandName,"TestLand00"+i);
            v.put(ColumnName.Land.LandLength,20);
            v.put(ColumnName.Land.LandWidth,30);
            v.put(ColumnName.Land.LandArea,1.25);
            v.put(ColumnName.Land.UserCreate,0);
            v.put(ColumnName.Land.Address,"Test Land Address 00"+i);
            v.put(ColumnName.Land.Sector,"A");
            v.put(ColumnName.Land.Latitude,99.9911);
            v.put(ColumnName.Land.Longitude,99.9911001);
            v.put(ColumnName.Land.YearCrossing,2015);
            v.put(ColumnName.Land.SugarcaneSelectionType,1);
            v.put(ColumnName.Land.createdTime,baseApplication.getTimeUTC());
            v.put(ColumnName.Land.updatedTime,baseApplication.getTimeUTC());
            v.put(ColumnName.Land.MaximumRow,20);
            v.put(ColumnName.Land.MaximumFamilyPerRow,2);
            v.put(ColumnName.Land.MaximumClonePerFamily,20);
            cr.insert(Database.LAND,v);
        }

    }

    private void DeleteSampleLandData(){
        ContentResolver cr = getContentResolver();
        cr.delete(Database.LAND,null,null);
    }
}
