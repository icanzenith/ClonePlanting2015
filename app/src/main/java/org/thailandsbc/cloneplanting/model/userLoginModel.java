package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/2/15 AD.
 */
public class userLoginModel implements Parcelable {
    private int UserID;
    private String Name;
    private String Sector;
    private String Tel;
    private String Email;
    private String PicURL;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPicURL() {
        return PicURL;
    }

    public void setPicURL(String picURL) {
        PicURL = picURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.UserID);
        dest.writeString(this.Name);
        dest.writeString(this.Sector);
        dest.writeString(this.Tel);
        dest.writeString(this.Email);
        dest.writeString(this.PicURL);
    }

    public userLoginModel() {
    }

    protected userLoginModel(Parcel in) {
        this.UserID = in.readInt();
        this.Name = in.readString();
        this.Sector = in.readString();
        this.Tel = in.readString();
        this.Email = in.readString();
        this.PicURL = in.readString();
    }

    public static final Parcelable.Creator<userLoginModel> CREATOR = new Parcelable.Creator<userLoginModel>() {
        public userLoginModel createFromParcel(Parcel source) {
            return new userLoginModel(source);
        }

        public userLoginModel[] newArray(int size) {
            return new userLoginModel[size];
        }
    };
}
