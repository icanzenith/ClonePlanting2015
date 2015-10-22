package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 8/31/15 AD.
 * for information of user
 */
public class UserDataModel implements Parcelable {

        public enum TAG
        {
            UserID,WorkPlaceCode,WorkPlaceFullName,
            FullName,Email,Position,Address,TelNumber
        }

    private Integer UserID;
    private String WorkPlaceCode;
    private String WorkPlaceFullName;
    private String FullName;
    private String Email;
    private String Position;
    private String Address;
    private String TelNumber;
    private String ProfilePictureURL;



    public UserDataModel createSample(){
        UserDataModel userDataModel = new UserDataModel();
        userDataModel.UserID = 68 ;
        userDataModel.FullName = "นายสมหมาย สมประสงค์";
        userDataModel.WorkPlaceCode = "A";
        userDataModel.WorkPlaceFullName = "Kasetsart University";
        userDataModel.TelNumber = "0915291949";
        userDataModel.Email = "r.jitpakorn@gmail.com";
        userDataModel.Position = "Android Developer";
        userDataModel.Address = "At Home Street number 1992 android 78892";
        return userDataModel;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getWorkPlaceCode() {
        return WorkPlaceCode;
    }

    public void setWorkPlaceCode(String workPlaceCode) {
        WorkPlaceCode = workPlaceCode;
    }

    public String getWorkPlaceFullName() {
        return WorkPlaceFullName;
    }

    public void setWorkPlaceFullName(String workPlaceFullName) {
        WorkPlaceFullName = workPlaceFullName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelNumber() {
        return TelNumber;
    }

    public void setTelNumber(String telNumber) {
        TelNumber = telNumber;
    }

    public String getProfilePictureURL() {
        return ProfilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        ProfilePictureURL = profilePictureURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.UserID);
        dest.writeString(this.WorkPlaceCode);
        dest.writeString(this.WorkPlaceFullName);
        dest.writeString(this.FullName);
        dest.writeString(this.Email);
        dest.writeString(this.Position);
        dest.writeString(this.Address);
        dest.writeString(this.TelNumber);
        dest.writeString(this.ProfilePictureURL);
    }

    public UserDataModel() {
    }

    protected UserDataModel(Parcel in) {
        this.UserID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.WorkPlaceCode = in.readString();
        this.WorkPlaceFullName = in.readString();
        this.FullName = in.readString();
        this.Email = in.readString();
        this.Position = in.readString();
        this.Address = in.readString();
        this.TelNumber = in.readString();
        this.ProfilePictureURL = in.readString();
    }

    public static final Parcelable.Creator<UserDataModel> CREATOR = new Parcelable.Creator<UserDataModel>() {
        public UserDataModel createFromParcel(Parcel source) {
            return new UserDataModel(source);
        }

        public UserDataModel[] newArray(int size) {
            return new UserDataModel[size];
        }
    };
}
