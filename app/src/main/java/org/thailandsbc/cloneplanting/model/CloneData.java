package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 11/11/2015 AD.
 */
public class CloneData implements Parcelable {
    private String CloneCode;
    private boolean isDead;
    private String FamilyCode;
    private String createTime;
    private String updateTime;
    private Integer LandID;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CloneCode);
        dest.writeByte(isDead ? (byte) 1 : (byte) 0);
        dest.writeString(this.FamilyCode);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeValue(this.LandID);
    }

    public CloneData() {
    }

    protected CloneData(Parcel in) {
        this.CloneCode = in.readString();
        this.isDead = in.readByte() != 0;
        this.FamilyCode = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.LandID = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CloneData> CREATOR = new Parcelable.Creator<CloneData>() {
        public CloneData createFromParcel(Parcel source) {
            return new CloneData(source);
        }

        public CloneData[] newArray(int size) {
            return new CloneData[size];
        }
    };

    public String getCloneCode() {
        return CloneCode;
    }

    public void setCloneCode(String cloneCode) {
        CloneCode = cloneCode;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public String getFamilyCode() {
        return FamilyCode;
    }

    public void setFamilyCode(String familyCode) {
        FamilyCode = familyCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLandID() {
        return LandID;
    }

    public void setLandID(Integer landID) {
        LandID = landID;
    }
}
