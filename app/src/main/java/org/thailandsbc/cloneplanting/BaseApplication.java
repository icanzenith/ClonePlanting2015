package org.thailandsbc.cloneplanting;

import android.app.Application;

import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.model.UserDataModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Icanzenith on 10/27/2015 AD.
 */
public class BaseApplication extends Application {


    public UserDataModel getUserData() {
        return userData;
    }

    public void setUserData(UserDataModel userData) {
        this.userData = userData;
    }

    private UserDataModel userData;

    public String getTimeUTC(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD\'T\'hh:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateUTC = sdf.format(new Date(calendar.getTimeInMillis()));
        return dateUTC;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //get Userdata model here
        MySharedPreference d = new MySharedPreference(this);
        userData = d.getUserData();

    }
}
