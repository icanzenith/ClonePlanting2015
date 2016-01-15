package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class    SendFamilyModel implements Parcelable {
    public String NameTent;
    public String SentBy;
    public String SentTo;
    public int SentAmount;
    public String UserSender;
    public int PositionInList;
    public String MotherCode;
    public String FatherCode;
    public String createdTime;
    public String updatedTime;
    public Integer ObjectID;

    public String getSentBy() {
        return SentBy;
    }

    public void setSentBy(String sentBy) {
        SentBy = sentBy;
    }

    public String getSentTo() {
        return SentTo;
    }

    public void setSentTo(String sentTo) {
        SentTo = sentTo;
    }

    public String getMotherCode() {
        return MotherCode;
    }

    public void setMotherCode(String motherCode) {
        MotherCode = motherCode;
    }

    public String getFatherCode() {
        return FatherCode;
    }

    public void setFatherCode(String fatherCode) {
        FatherCode = fatherCode;
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

    public int getSentAmount() {
        return SentAmount;
    }

    public void setSentAmount(int sentAmount) {
        SentAmount = sentAmount;
    }

    public String getUserSender() {
        return UserSender;
    }

    public void setUserSender(String userSender) {
        UserSender = userSender;
    }

    public int getPositionInList() {
        return PositionInList;
    }

    public void setPositionInList(int positionInList) {
        PositionInList = positionInList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.NameTent);
        dest.writeString(this.SentBy);
        dest.writeString(this.SentTo);
        dest.writeInt(this.SentAmount);
        dest.writeString(this.UserSender);
        dest.writeInt(this.PositionInList);
        dest.writeString(this.MotherCode);
        dest.writeString(this.FatherCode);
        dest.writeString(this.createdTime);
        dest.writeString(this.updatedTime);
    }

    public SendFamilyModel() {
    }

    private SendFamilyModel(Parcel in) {
        this.NameTent = in.readString();
        this.SentBy = in.readString();
        this.SentTo = in.readString();
        this.SentAmount = in.readInt();
        this.UserSender = in.readString();
        this.PositionInList = in.readInt();
        this.MotherCode = in.readString();
        this.FatherCode = in.readString();
        this.createdTime = in.readString();
        this.updatedTime = in.readString();
    }

    public static final Parcelable.Creator<SendFamilyModel> CREATOR = new Parcelable.Creator<SendFamilyModel>() {
        public SendFamilyModel createFromParcel(Parcel source) {
            return new SendFamilyModel(source);
        }

        public SendFamilyModel[] newArray(int size) {
            return new SendFamilyModel[size];
        }
    };
}
