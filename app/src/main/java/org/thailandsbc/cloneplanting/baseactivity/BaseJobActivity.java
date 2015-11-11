package org.thailandsbc.cloneplanting.baseactivity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Icanzenith on 10/30/2015 AD.
 */
public abstract class BaseJobActivity extends AppCompatActivity{

    public abstract void InitializationViews();
    public abstract void insertDataToDatabase();
    public abstract void updateDataToDatabase();
    public abstract void deleteDataFromDatabase();
    public abstract void addTooList();
    public abstract void deleteDataFromList();
    public abstract void refreshListData();

}
