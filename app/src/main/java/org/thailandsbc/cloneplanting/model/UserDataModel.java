package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 8/31/15 AD.
 * for information of user
 */
public class UserDataModel implements Parcelable {
    private Integer UserID;
    private String WorkPlace;
    private String WorkPlaceFullName;
    private String FullName;
    private String Email;
    private String Position;
    private String Address;
    private String TelNumber;



    public UserDataModel createSample(){
        UserDataModel userDataModel = new UserDataModel();
        userDataModel.UserID = 68 ;
        userDataModel.FullName = "นายสมหมาย สมประสงค์";
        userDataModel.WorkPlace = "A";
        userDataModel.WorkPlaceFullName = "Kasetsart University";
        userDataModel.TelNumber = "0915291949";
        userDataModel.Email = "r.jitpakorn@gmail.com";
        userDataModel.Position = "Android Developer";
        userDataModel.Address = "At Home Street number 1992 android 78892";
        return userDataModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.UserID);
        dest.writeString(this.WorkPlace);
        dest.writeString(this.WorkPlaceFullName);
        dest.writeString(this.FullName);
        dest.writeString(this.Email);
        dest.writeString(this.Position);
        dest.writeString(this.Address);
        dest.writeString(this.TelNumber);
    }

    public UserDataModel() {
    }

    protected UserDataModel(Parcel in) {
        this.UserID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.WorkPlace = in.readString();
        this.WorkPlaceFullName = in.readString();
        this.FullName = in.readString();
        this.Email = in.readString();
        this.Position = in.readString();
        this.Address = in.readString();
        this.TelNumber = in.readString();
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
