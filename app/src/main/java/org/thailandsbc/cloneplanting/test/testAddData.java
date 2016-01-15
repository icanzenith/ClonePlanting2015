package org.thailandsbc.cloneplanting.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.SugarcaneSelectionType;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.utils.PlantStatus;

/**
 * Created by Icanzenith on 11/10/2015 AD.
 */
public class testAddData extends AppCompatActivity {

    private BaseApplication baseApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getApplication();
        setContentView(R.layout.content_create_land);

        //Add Receive Data;
        testAddReceiveData();

    }

    private void testAddReceiveData() {
        String[] listData = {"A15380"
                , "A15381"
                , "A15382"
                , "A15383"
                , "A15384"
                , "A15385"
                , "A15386"
                , "A15387"
                , "A15388"
                , "A15389"
                , "A15390"
                , "A15391"
                , "A15392"
                , "A15393"
                , "A15394"
                , "A15395"
                , "A15396"
        };

        for (String s : listData) {
            ContentValues v = new ContentValues();
            v.put(ColumnName.ReceivedClone.NameTent, s);
            v.put(ColumnName.ReceivedClone.SentBy, "A");
            v.put(ColumnName.ReceivedClone.ReceivedBy, "C");
            v.put(ColumnName.ReceivedClone.UserReceiver, 11);
            v.put(ColumnName.ReceivedClone.ReceivedAmount, 35);
            v.put(ColumnName.ReceivedClone.createdTime, baseApplication.getTimeUTC());
            v.put(ColumnName.ReceivedClone.updatedTime, baseApplication.getTimeUTC());
            v.put(ColumnName.ReceivedClone.MotherCode, "RT00956");
            v.put(ColumnName.ReceivedClone.FatherCode, "Khon Khean 3");
            v.put(ColumnName.ReceivedClone.isPlanted, PlantStatus.NOT_Planted);
            Uri insert = getContentResolver().insert(Database.RECEIVEDCLONE, v);
            Log.d("Insert", insert.toString());
        }

    }

    /**
     * @Example Land Data
     * */
    private void testAddLandDetail() {

        ContentValues c = new ContentValues();
        c.put(ColumnName.Land.LandName,"AMM001");
        c.put(ColumnName.Land.LandLength,40);
        c.put(ColumnName.Land.LandWidth,40);
        c.put(ColumnName.Land.LandArea,1600);
        c.put(ColumnName.Land.UserCreate,8);
        c.put(ColumnName.Land.Sector,"A");
        c.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c.put(ColumnName.Land.Address,"แปลงทดลองการเกษตรภาควิชาโรคพืช 1 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c.put(ColumnName.Land.createdTime,baseApplication.getTimeUTC());
        c.put(ColumnName.Land.updatedTime,baseApplication.getTimeUTC());
        c.put(ColumnName.Land.MaximumRow,26);
        c.put(ColumnName.Land.MaximumClonePerFamily,35);
        c.put(ColumnName.Land.MaximumFamilyPerRow,1);
        c.put(ColumnName.Land.Latitude,14.028375);
        c.put(ColumnName.Land.Longitude,99.966986);
        c.put(ColumnName.Land.YearCrossing,2014);


        //Land 2
        ContentValues c2 = new ContentValues();
        c.put(ColumnName.Land.LandName,"AMM002");
        c2.put(ColumnName.Land.LandLength,40);
        c2.put(ColumnName.Land.LandWidth,40);
        c2.put(ColumnName.Land.LandArea,1600);
        c2.put(ColumnName.Land.UserCreate,8);
        c2.put(ColumnName.Land.Sector,"A");
        c2.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c2.put(ColumnName.Land.Address,"แปลงทดลองการเกษตรภาควิชาโรคพืช 1 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c2.put(ColumnName.Land.createdTime,baseApplication.getTimeUTC());
        c2.put(ColumnName.Land.updatedTime,baseApplication.getTimeUTC());
        c2.put(ColumnName.Land.MaximumRow,26);
        c2.put(ColumnName.Land.MaximumClonePerFamily,35);
        c2.put(ColumnName.Land.MaximumFamilyPerRow,1);
        c2.put(ColumnName.Land.Latitude,14.027954);
        c2.put(ColumnName.Land.Longitude,99.966975);
        c2.put(ColumnName.Land.YearCrossing,2014);

        //Land 3

        ContentValues c3 = new ContentValues();
        c.put(ColumnName.Land.LandName,"AMM003");
        c3.put(ColumnName.Land.LandLength,40);
        c3.put(ColumnName.Land.LandWidth,40);
        c3.put(ColumnName.Land.LandArea,1600);
        c3.put(ColumnName.Land.UserCreate,8);
        c3.put(ColumnName.Land.Sector,"A");
        c3.put(ColumnName.Land.SugarcaneSelectionType, SugarcaneSelectionType.FIRST_SELECTION);
        c3.put(ColumnName.Land.Address,"แปลงทดลองการเกษตรภาควิชาโรคพืช 1 ภาควิชาเกษตรกลวิธาน มหาวิทยาลัยเกษตรศาสตร์วิทยาเขตกำแพงแสน");
        c3.put(ColumnName.Land.createdTime,baseApplication.getTimeUTC());
        c3.put(ColumnName.Land.updatedTime,baseApplication.getTimeUTC());
        c3.put(ColumnName.Land.MaximumRow,26);
        c3.put(ColumnName.Land.MaximumClonePerFamily,35);
        c3.put(ColumnName.Land.MaximumFamilyPerRow,1);
        c3.put(ColumnName.Land.Latitude,14.027924);
        c3.put(ColumnName.Land.Longitude,99.966996);
        c3.put(ColumnName.Land.YearCrossing,2014);

        getContentResolver().insert(Database.LAND,c);
        getContentResolver().insert(Database.LAND,c2);
        getContentResolver().insert(Database.LAND,c3);
    }

}
