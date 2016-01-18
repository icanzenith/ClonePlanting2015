package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 10/30/2015 AD.
 */
public class LandDetailModel implements Parcelable {
    public Integer LandID;
    public String LandName;
    public Double Latitude;
    public Double Longitude;
    public Float LandArea;
    public Float LandWidth;
    public Float LandLength;
    public Integer UserCreate;
    public String createdTime;
    public String updatedTime;
    public String Address;
    public Integer SugarcaneSelectionType;
    public String YearCrossing;
    public Integer PositionInList;
    public String Sector;
    public Integer MaximumRow;
    public Integer MaximumFamilyPerRow;
    public Integer MaximumClonePerFamily;




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.LandID);
        dest.writeString(this.LandName);
        dest.writeValue(this.Latitude);
        dest.writeValue(this.Longitude);
        dest.writeValue(this.LandArea);
        dest.writeValue(this.LandWidth);
        dest.writeValue(this.LandLength);
        dest.writeValue(this.UserCreate);
        dest.writeString(this.createdTime);
        dest.writeString(this.updatedTime);
        dest.writeString(this.Address);
        dest.writeValue(this.SugarcaneSelectionType);
        dest.writeString(this.YearCrossing);
        dest.writeValue(this.PositionInList);
        dest.writeString(this.Sector);
        dest.writeValue(this.MaximumRow);
        dest.writeValue(this.MaximumFamilyPerRow);
        dest.writeValue(this.MaximumClonePerFamily);
    }

    public LandDetailModel() {
    }

    protected LandDetailModel(Parcel in) {
        this.LandID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.LandName = in.readString();
        this.Latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.Longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.LandArea = (Float) in.readValue(Float.class.getClassLoader());
        this.LandWidth = (Float) in.readValue(Float.class.getClassLoader());
        this.LandLength = (Float) in.readValue(Float.class.getClassLoader());
        this.UserCreate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdTime = in.readString();
        this.updatedTime = in.readString();
        this.Address = in.readString();
        this.SugarcaneSelectionType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YearCrossing = in.readString();
        this.PositionInList = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Sector = in.readString();
        this.MaximumRow = (Integer) in.readValue(Integer.class.getClassLoader());
        this.MaximumFamilyPerRow = (Integer) in.readValue(Integer.class.getClassLoader());
        this.MaximumClonePerFamily = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<LandDetailModel> CREATOR = new Parcelable.Creator<LandDetailModel>() {
        public LandDetailModel createFromParcel(Parcel source) {
            return new LandDetailModel(source);
        }

        public LandDetailModel[] newArray(int size) {
            return new LandDetailModel[size];
        }
    };

    public Integer getLandID() {
        return LandID;
    }

    public void setLandID(Integer landID) {
        LandID = landID;
    }

    public String getLandName() {
        return LandName;
    }

    public void setLandName(String landName) {
        LandName = landName;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Float getLandArea() {
        return LandArea;
    }

    public void setLandArea(Float landArea) {
        LandArea = landArea;
    }

    public Float getLandWidth() {
        return LandWidth;
    }

    public void setLandWidth(Float landWidth) {
        LandWidth = landWidth;
    }

    public Float getLandLength() {
        return LandLength;
    }

    public void setLandLength(Float landLength) {
        LandLength = landLength;
    }

    public Integer getUserCreate() {
        return UserCreate;
    }

    public void setUserCreate(Integer userCreate) {
        UserCreate = userCreate;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getSugarcaneSelectionType() {
        return SugarcaneSelectionType;
    }

    public void setSugarcaneSelectionType(Integer sugarcaneSelectionType) {
        SugarcaneSelectionType = sugarcaneSelectionType;
    }

    public String getYearCrossing() {
        return YearCrossing;
    }

    public void setYearCrossing(String yearCrossing) {
        YearCrossing = yearCrossing;
    }

    public Integer getPositionInList() {
        return PositionInList;
    }

    public void setPositionInList(Integer positionInList) {
        PositionInList = positionInList;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public Integer getMaximumRow() {
        return MaximumRow;
    }

    public void setMaximumRow(Integer maximumRow) {
        MaximumRow = maximumRow;
    }

    public Integer getMaximumFamilyPerRow() {
        return MaximumFamilyPerRow;
    }

    public void setMaximumFamilyPerRow(Integer maximumFamilyPerRow) {
        MaximumFamilyPerRow = maximumFamilyPerRow;
    }

    public Integer getMaximumClonePerFamily() {
        return MaximumClonePerFamily;
    }

    public void setMaximumClonePerFamily(Integer maximumClonePerFamily) {
        MaximumClonePerFamily = maximumClonePerFamily;
    }

    public static Creator<LandDetailModel> getCREATOR() {
        return CREATOR;
    }
}
