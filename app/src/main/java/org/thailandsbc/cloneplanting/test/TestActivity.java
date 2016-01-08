package org.thailandsbc.cloneplanting.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.SugarcaneSelectionType;
import org.thailandsbc.cloneplanting.customview.CustomViewExampleActivity;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.database.MySQLiteOpenHelper;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.utils.PlantStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

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
                testAddLandDetail();
                testAddSentClone();
                testAddReceiveData();
            }
        });
        buttonGenerateLandData.setText("Test Simulate All Data");

        buttonDeleteAllLandData = (Button) findViewById(R.id.buttonDeleteAllLandData);
        buttonDeleteAllLandData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropAllData();
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


    private void AddSampleLandData() {
        //TODO Test Add Event แปลง
        ContentResolver cr = getContentResolver();
        for (int i = 0; i < 10; i++) {
            ContentValues v = new ContentValues();
            v.put(ColumnName.Land.LandName, "TestLand00" + i);
            v.put(ColumnName.Land.LandLength, 20);
            v.put(ColumnName.Land.LandWidth, 30);
            v.put(ColumnName.Land.LandArea, 1.25);
            v.put(ColumnName.Land.UserCreate, 0);
            v.put(ColumnName.Land.Address, "Test Land Address 00" + i);
            v.put(ColumnName.Land.Sector, "A");
            v.put(ColumnName.Land.Latitude, 99.9911);
            v.put(ColumnName.Land.Longitude, 99.9911001);
            v.put(ColumnName.Land.YearCrossing, 2015);
            v.put(ColumnName.Land.SugarcaneSelectionType, 1);
            v.put(ColumnName.Land.createdTime, baseApplication.getTimeUTC());
            v.put(ColumnName.Land.updatedTime, baseApplication.getTimeUTC());
            v.put(ColumnName.Land.MaximumRow, 20);
            v.put(ColumnName.Land.MaximumFamilyPerRow, 2);
            v.put(ColumnName.Land.MaximumClonePerFamily, 20);
            cr.insert(Database.LAND, v);
        }

    }

    private void testAddLandDetail() {

        ContentValues c = new ContentValues();
        c.put(ColumnName.Land.LandName, "AMM001");
        c.put(ColumnName.Land.LandID,1);
        c.put(ColumnName.Land.LandLength, 40);
        c.put(ColumnName.Land.LandWidth, 40);
        c.put(ColumnName.Land.LandArea, 1600);
        c.put(ColumnName.Land.UserCreate, 8);
        c.put(ColumnName.Land.Sector, "A");
        c.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c.put(ColumnName.Land.Address, "แปลงทดลองการเกษตรภาควิชาโรคพืช 1 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c.put(ColumnName.Land.createdTime, baseApplication.getTimeUTC());
        c.put(ColumnName.Land.updatedTime, baseApplication.getTimeUTC());
        c.put(ColumnName.Land.MaximumRow, 26);
        c.put(ColumnName.Land.MaximumClonePerFamily, 35);
        c.put(ColumnName.Land.MaximumFamilyPerRow, 1);
        c.put(ColumnName.Land.Latitude, 14.028375);
        c.put(ColumnName.Land.Longitude, 99.966986);
        c.put(ColumnName.Land.YearCrossing, 2014);


        //Land 2
        ContentValues c2 = new ContentValues();
        c2.put(ColumnName.Land.LandName, "AMM002");
        c2.put(ColumnName.Land.LandID,2);
        c2.put(ColumnName.Land.LandLength, 40);
        c2.put(ColumnName.Land.LandWidth, 40);
        c2.put(ColumnName.Land.LandArea, 1600);
        c2.put(ColumnName.Land.UserCreate, 8);
        c2.put(ColumnName.Land.Sector, "A");
        c2.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c2.put(ColumnName.Land.Address, "แปลงทดลองการเกษตรภาควิชาโรคพืช 2 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c2.put(ColumnName.Land.createdTime, baseApplication.getTimeUTC());
        c2.put(ColumnName.Land.updatedTime, baseApplication.getTimeUTC());
        c2.put(ColumnName.Land.MaximumRow, 26);
        c2.put(ColumnName.Land.MaximumClonePerFamily, 35);
        c2.put(ColumnName.Land.MaximumFamilyPerRow, 1);
        c2.put(ColumnName.Land.Latitude, 14.027954);
        c2.put(ColumnName.Land.Longitude, 99.966975);
        c2.put(ColumnName.Land.YearCrossing, 2014);

        //Land 3

        ContentValues c3 = new ContentValues();
        c3.put(ColumnName.Land.LandName, "AMM003");
        c3.put(ColumnName.Land.LandID,3);
        c3.put(ColumnName.Land.LandLength, 40);
        c3.put(ColumnName.Land.LandWidth, 40);
        c3.put(ColumnName.Land.LandArea, 1600);
        c3.put(ColumnName.Land.UserCreate, 8);
        c3.put(ColumnName.Land.Sector, "A");
        c3.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c3.put(ColumnName.Land.Address, "แปลงทดลองการเกษตรภาควิชาโรคพืช 3 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c3.put(ColumnName.Land.createdTime, baseApplication.getTimeUTC());
        c3.put(ColumnName.Land.updatedTime, baseApplication.getTimeUTC());
        c3.put(ColumnName.Land.MaximumRow, 26);
        c3.put(ColumnName.Land.MaximumClonePerFamily, 35);
        c3.put(ColumnName.Land.MaximumFamilyPerRow, 1);
        c3.put(ColumnName.Land.Latitude, 14.027924);
        c3.put(ColumnName.Land.Longitude, 99.966996);
        c3.put(ColumnName.Land.YearCrossing, 2014);

        getContentResolver().insert(Database.LAND, c);
        getContentResolver().insert(Database.LAND, c2);
        getContentResolver().insert(Database.LAND, c3);
    }

    private void DeleteSampleLandData() {
        ContentResolver cr = getContentResolver();
        cr.delete(Database.LAND, null, null);
    }

    /**
     *
     *
     *
     * */

    private String getParentRandom() {
        String name = null;
        ArrayList<String> parentList = new ArrayList<>();
        parentList.add("LK11");
        parentList.add("UT8");
        parentList.add("UT12");
        parentList.add("KK3");
        parentList.add("RT027-11");
        parentList.add("A14398-009");
        parentList.add("OP");

        Collections.shuffle(parentList);
        name = parentList.get(1);

        return name;
    }

    private void testAddSentClone() {
        String[] sentClone = {
                "A15022",
                "A15023",
                "A15024",
                "A15025",
                "A15026",
                "A15027",
                "A15028",
                "A15029",
                "A15030",
                "A15031",
                "A15032",
                "A15033",
                "A15034",
                "A15035",
                "A15036",
                "A15037",
                "A15038"
        };
        for(String place : getResources().getStringArray(R.array.place)) {

            for (int i = 0 ;i < sentClone.length ;i ++) {
                ContentValues v = new ContentValues();
                v.put(ColumnName.SentClone.FamilyCode,sentClone[i]);
                v.put(ColumnName.SentClone.SentBy,"A");
                v.put(ColumnName.SentClone.SentTo,place);
                v.put(ColumnName.SentClone.SentAmount,20);
                v.put(ColumnName.SentClone.UserSender,11);
                v.put(ColumnName.SentClone.createdTime,baseApplication.getTimeUTC());
                v.put(ColumnName.SentClone.updatedTime,baseApplication.getTimeUTC());
                v.put(ColumnName.SentClone.MotherCode,getParentRandom());
                v.put(ColumnName.SentClone.FatherCode,getParentRandom());

            }


        }
    }


    private void testAddReceiveData() {
        int mPlantedAmount = 35;

        for (String place : getResources().getStringArray(R.array.place)) {
            // Place by Place
            String[] listData = {place + "15380"
                    , place + "15381"
                    , place + "15382"
                    , place + "15383"
                    , place + "15384"
                    , place + "15385"
                    , place + "15386"
                    , place + "15387"
                    , place + "15388"
                    , place + "15389"
                    , place + "15390"
                    , place + "15391"
                    , place + "15392"
                    , place + "15393"
                    , place + "15394"
                    , place + "15395"
                    , place + "15396"};
            if (place.equals("All")) {
                //do nothing
            } else {
                String nameTent;

                for (int i  = 0; i < listData.length ;i++) {
                    nameTent = listData[i];
                    ContentValues v = new ContentValues();

                    v.put(ColumnName.ReceivedClone.FamilyCode, listData[i]);
                    v.put(ColumnName.ReceivedClone.SentBy, place);
                    v.put(ColumnName.ReceivedClone.ReceivedBy, "A");
                    v.put(ColumnName.ReceivedClone.UserReceiver, 11);
                    v.put(ColumnName.ReceivedClone.ReceivedAmount, 35);
                    v.put(ColumnName.ReceivedClone.createdTime, baseApplication.getTimeUTC());
                    v.put(ColumnName.ReceivedClone.updatedTime, baseApplication.getTimeUTC());
                    v.put(ColumnName.ReceivedClone.MotherCode, getParentRandom());
                    v.put(ColumnName.ReceivedClone.FatherCode, getParentRandom());
                    v.put(ColumnName.ReceivedClone.isPlanted, PlantStatus.Planted);
                    v.put(ColumnName.ReceivedClone.RowNumber,(i+1));
                    v.put(ColumnName.ReceivedClone.OrderInRow,1);
                    v.put(ColumnName.ReceivedClone.PlantedAmount,mPlantedAmount);
                    if (place.equals("A")){
                        v.put(ColumnName.Land.LandID,1);
                        insertClonePlanted(nameTent,mPlantedAmount,1);
                    }else if (place.equals("G")){
                        v.put(ColumnName.Land.LandID,2);
                        insertClonePlanted(nameTent,mPlantedAmount,2);
                    }else if(place.equals("L")){
                        v.put(ColumnName.Land.LandID,3);
                        insertClonePlanted(nameTent,mPlantedAmount,2);
                    }
                    Uri insert = getContentResolver().insert(Database.RECEIVEDCLONE, v);

                }
            }
        }
    }

    private void insertClonePlanted(String nameTent , int plantedAmount,int landID){
        //TODO Insert Planted Data
        for (int j = 0 ; j< plantedAmount; j++){
            //Uri Planted Amount
            String cloneCode = String.format(nameTent+ "-%03d", (j + 1));
            ContentValues c = new ContentValues();
            c.put(ColumnName.PlantedClone.CloneCode,cloneCode);
            if (Math.random() < 0.2){
                c.put(ColumnName.PlantedClone.isDead,0);
            } else {
                c.put(ColumnName.PlantedClone.isDead,1);
            }
            c.put(ColumnName.PlantedClone.createdTime,baseApplication.getTimeUTC());
            c.put(ColumnName.PlantedClone.updatedTime,baseApplication.getTimeUTC());
            c.put(ColumnName.PlantedClone.LandID,landID);
            c.put(ColumnName.PlantedClone.FamilyCode,nameTent);
            getContentResolver().insert(Database.PLANTEDCLONE,c);
        }
    }
    private void dropAllData(){
        MySQLiteOpenHelper q = new MySQLiteOpenHelper(this, Database.AUTHORITY, null, 1);
        SQLiteDatabase d = q.getReadableDatabase();
        q.onUpgrade(d,1,1);
    }


}


