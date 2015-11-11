package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class    SendFamilyModel implements Parcelable {
    private String FamilyCode;
    private String SentBy;
    private String SentTo;
    private int SendAmount;
    private String UserSender;
    private int PositionInList;
    private String MotherCode;
    private String FatherCode;
    private String createdTime;
    private String updatedTime;

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

    public String getFamilyCode() {
        return FamilyCode;
    }

    public void setFamilyCode(String familyCode) {
        FamilyCode = familyCode;
    }

    public int getSendAmount() {
        return SendAmount;
    }

    public void setSendAmount(int sendAmount) {
        SendAmount = sendAmount;
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
        dest.writeString(this.FamilyCode);
        dest.writeString(this.SentBy);
        dest.writeString(this.SentTo);
        dest.writeInt(this.SendAmount);
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
        this.FamilyCode = in.readString();
        this.SentBy = in.readString();
        this.SentTo = in.readString();
        this.SendAmount = in.readInt();
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
