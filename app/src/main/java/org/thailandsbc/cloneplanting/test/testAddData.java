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
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
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

        //Add Receiveive Data;
        testAddRecieveData();

    }

    private void testAddRecieveData() {
        String[] listdata = {"A15380"
       ,"A15381"
       ,"A15382"
       ,"A15383"
       ,"A15384"
       ,"A15385"
       ,"A15386"
       ,"A15387"
       ,"A15388"
       ,"A15389"
       ,"A15390"
       ,"A15391"
       ,"A15392"
       ,"A15393"
       ,"A15394"
       ,"A15395"
       ,"A15396"
        };

        for(String s : listdata)  {
            ContentValues v = new ContentValues();
            v.put(ColumnName.ReceivedClone.FamilyCode   ,s);
            v.put(ColumnName.ReceivedClone.SentBy       ,"A");
            v.put(ColumnName.ReceivedClone.ReceivedBy   ,"C");
            v.put(ColumnName.ReceivedClone.UserReceiver ,11);
            v.put(ColumnName.ReceivedClone.ReceivedAmount,35);
            v.put(ColumnName.ReceivedClone.createdTime  ,baseApplication.getTimeUTC());
            v.put(ColumnName.ReceivedClone.updatedTime  ,baseApplication.getTimeUTC());
            v.put(ColumnName.ReceivedClone.MotherCode   ,"RT00956");
            v.put(ColumnName.ReceivedClone.FatherCode   ,"Khon Khean 3");
            v.put(ColumnName.ReceivedClone.isPlanted, PlantStatus.NOT_Planted);
            Uri insert = getContentResolver().insert(Database.RECEIVEDCLONE, v);
            Log.d("Insert",insert.toString());
        }

    }


}
