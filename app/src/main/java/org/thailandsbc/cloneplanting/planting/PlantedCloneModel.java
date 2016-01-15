package org.thailandsbc.cloneplanting.planting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 10/30/2015 AD.
 */
public class PlantedCloneModel implements Parcelable {

    public int            ObjectID;
    public String          CloneCode;
    public int            isDead;
    public String         createdTime;
    public String         updatedTime;
    public String         NameTent;
    public String         LandID;

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    public String getCloneCode() {
        return CloneCode;
    }

    public void setCloneCode(String cloneCode) {
        CloneCode = cloneCode;
    }

    public int getIsDead() {
        return isDead;
    }

    public void setIsDead(int isDead) {
        this.isDead = isDead;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getNameTent() {
        return NameTent;
    }

    public void setNameTent(String nameTent) {
        NameTent = nameTent;
    }

    public String getLandID() {
        return LandID;
    }

    public void setLandID(String landID) {
        LandID = landID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ObjectID);
        dest.writeString(this.CloneCode);
        dest.writeInt(this.isDead);
        dest.writeString(this.createdTime);
        dest.writeString(this.updatedTime);
        dest.writeString(this.NameTent);
        dest.writeString(this.LandID);
    }

    public PlantedCloneModel() {
    }

    protected PlantedCloneModel(Parcel in) {
        this.ObjectID = in.readInt();
        this.CloneCode = in.readString();
        this.isDead = in.readInt();
        this.createdTime = in.readString();
        this.updatedTime = in.readString();
        this.NameTent = in.readString();
        this.LandID = in.readString();
    }

    public static final Parcelable.Creator<PlantedCloneModel> CREATOR = new Parcelable.Creator<PlantedCloneModel>() {
        public PlantedCloneModel createFromParcel(Parcel source) {
            return new PlantedCloneModel(source);
        }

        public PlantedCloneModel[] newArray(int size) {
            return new PlantedCloneModel[size];
        }
    };
}