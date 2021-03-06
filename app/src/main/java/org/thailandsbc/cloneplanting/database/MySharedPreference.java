package org.thailandsbc.cloneplanting.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.thailandsbc.cloneplanting.model.UserDataModel;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */

public class MySharedPreference {

    public static final String PREF_NAME = "ClonePlantingSharedPreference";
    private static final String TAG = "MyShared Prefference";
    private SharedPreferences.Editor mEditor;

    private static final String IS_LOGIN = "IsLoggedIn";

    private SharedPreferences mPreferences;
    private Context mContext;

    public MySharedPreference(Context context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.commit();

    }

    public void CreatedSessionLogin(UserDataModel userData) {

        //Create First LoginHere

        mEditor.putInt(UserDataModel.TAG.UserID.toString(), userData.getUserID());
        mEditor.putString(UserDataModel.TAG.FullName.toString(), userData.getFullName());
        mEditor.putString(UserDataModel.TAG.WorkPlaceCode.toString(), userData.getWorkPlaceCode());
        mEditor.putString(UserDataModel.TAG.WorkPlaceFullName.toString(),userData.getWorkPlaceFullName());
        mEditor.putString(UserDataModel.TAG.Email.toString(), userData.getEmail());
        mEditor.putString(UserDataModel.TAG.Address.toString(), userData.getAddress());
        mEditor.putString(UserDataModel.TAG.Position.toString(),userData.getPosition());
        mEditor.putString(UserDataModel.TAG.TelNumber.toString(),userData.getTelNumber());
        mEditor.putString(UserDataModel.TAG.URL_PICTURE_PROFILE.toString(),userData.getProfilePictureURL());
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.commit();
        Log.d(TAG, "CreatedSessionLogin: "+userData.getProfilePictureURL().toString());
    }

    public void Logout() {
        mEditor.clear();
        mEditor.commit();
    }

    public UserDataModel getUserData(){

        UserDataModel userData = new UserDataModel();
        userData.setUserID(mPreferences.getInt(UserDataModel.TAG.UserID.toString(),0));
        userData.setFullName(mPreferences.getString(UserDataModel.TAG.FullName.toString(),""));
        userData.setEmail(mPreferences.getString(UserDataModel.TAG.Email.toString(), ""));
        userData.setWorkPlaceCode(mPreferences.getString(UserDataModel.TAG.WorkPlaceCode.toString(), ""));
        userData.setWorkPlaceFullName(mPreferences.getString(UserDataModel.TAG.WorkPlaceFullName.toString(), ""));
        userData.setTelNumber(mPreferences.getString(UserDataModel.TAG.TelNumber.toString(), ""));
        userData.setAddress(mPreferences.getString(UserDataModel.TAG.Address.toString(),""));
        userData.setProfilePictureURL(mPreferences.getString(UserDataModel.TAG.URL_PICTURE_PROFILE.toString(),""));


        return userData;
    }

    public boolean isLoggedIn() {
        return mPreferences.getBoolean(IS_LOGIN, false);
    }
}
