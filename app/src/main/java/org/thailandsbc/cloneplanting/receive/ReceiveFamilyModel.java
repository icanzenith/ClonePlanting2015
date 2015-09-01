package org.thailandsbc.cloneplanting.receive;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class ReceiveFamilyModel implements Parcelable {
    private int order;
    private String FamilyCode;
    private int SendAmount;
    private String SenderID;
    private int PositionInList;

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
        dest.writeInt(this.order);
        dest.writeString(this.FamilyCode);
        dest.writeInt(this.SendAmount);
        dest.writeString(this.SenderID);
        dest.writeInt(this.PositionInList);
    }

    public ReceiveFamilyModel() {
    }

    protected ReceiveFamilyModel(Parcel in) {
        this.order = in.readInt();
        this.FamilyCode = in.readString();
        this.SendAmount = in.readInt();
        this.SenderID = in.readString();
        this.PositionInList = in.readInt();
    }

    public static final Parcelable.Creator<ReceiveFamilyModel> CREATOR = new Parcelable.Creator<ReceiveFamilyModel>() {
        public ReceiveFamilyModel createFromParcel(Parcel source) {
            return new ReceiveFamilyModel(source);
        }

        public ReceiveFamilyModel[] newArray(int size) {
            return new ReceiveFamilyModel[size];
        }
    };
}
