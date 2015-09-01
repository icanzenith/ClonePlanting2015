package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class SendFamilyModel implements Parcelable {
    private int order;
    private String FamilyCode;
    private int SendAmount;
    private String SenderID;
    private int PostionInList;



    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getSenderID() {
        return SenderID;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public int getPostionInList() {
        return PostionInList;
    }

    public void setPostionInList(int postionInList) {
        PostionInList = postionInList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.order);
        dest.writeString(this.FamilyCode);
        dest.writeInt(this.SendAmount);
        dest.writeString(this.SenderID);
        dest.writeInt(this.PostionInList);
    }

    public SendFamilyModel() {
    }

    protected SendFamilyModel(Parcel in) {
        this.order = in.readInt();
        this.FamilyCode = in.readString();
        this.SendAmount = in.readInt();
        this.SenderID = in.readString();
        this.PostionInList = in.readInt();
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
