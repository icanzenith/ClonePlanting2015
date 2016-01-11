package org.thailandsbc.cloneplanting.receive;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class ReceiveFamilyModel implements Parcelable {
    private String  FamilyCode;
    private String  SentBy;
    private String  ReceivedBy;
    private int     UserReceiver;
    private int     ReceivedAmount;
    private String  createdTime;
    private String  updatedTime;
    private String  MotherCode;
    private String  FatherCode;
    private boolean isPlanted;
    private int     PlantedBy;
    private int     PlantedAmount;
    private int     RowNumber;
    private int     OrderinRow;
    private String  PlantedTime;
    private int     LandID;
    private int     PositionInList;

    private int     SurviveAmount;
    private int     Order;


    public String getFamilyCode() {
        return FamilyCode;
    }

    public void setFamilyCode(String familyCode) {
        FamilyCode = familyCode;
    }

    public String getSentBy() {
        return SentBy;
    }

    public void setSentBy(String sentBy) {
        SentBy = sentBy;
    }

    public String getReceivedBy() {
        return ReceivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        ReceivedBy = receivedBy;
    }

    public int getUserReceiver() {
        return UserReceiver;
    }

    public void setUserReceiver(int userReceiver) {
        UserReceiver = userReceiver;
    }

    public int getReceivedAmount() {
        return ReceivedAmount;
    }

    public void setReceivedAmount(int receivedAmount) {
        ReceivedAmount = receivedAmount;
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

    public boolean isPlanted() {
        return isPlanted;
    }

    public void setPlanted(boolean planted) {
        isPlanted = planted;
    }

    public int getPlantedBy() {
        return PlantedBy;
    }

    public void setPlantedBy(int plantedBy) {
        PlantedBy = plantedBy;
    }

    public int getPlantedAmount() {
        return PlantedAmount;
    }

    public void setPlantedAmount(int plantedAmount) {
        PlantedAmount = plantedAmount;
    }

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int rowNumber) {
        RowNumber = rowNumber;
    }

    public int getOrderinRow() {
        return OrderinRow;
    }

    public void setOrderinRow(int orderinRow) {
        OrderinRow = orderinRow;
    }

    public String getPlantedTime() {
        return PlantedTime;
    }

    public void setPlantedTime(String plantedTime) {
        PlantedTime = plantedTime;
    }

    public int getLandID() {
        return LandID;
    }

    public void setLandID(int landID) {
        LandID = landID;
    }

    public int getPositionInList() {
        return PositionInList;
    }

    public void setPositionInList(int positionInList) {
        PositionInList = positionInList;
    }

    public int getSurviveAmount() {
        return SurviveAmount;
    }

    public void setSurviveAmount(int surviveAmount) {
        SurviveAmount = surviveAmount;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    public static Creator<ReceiveFamilyModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FamilyCode);
        dest.writeString(this.SentBy);
        dest.writeString(this.ReceivedBy);
        dest.writeInt(this.UserReceiver);
        dest.writeInt(this.ReceivedAmount);
        dest.writeString(this.createdTime);
        dest.writeString(this.updatedTime);
        dest.writeString(this.MotherCode);
        dest.writeString(this.FatherCode);
        dest.writeByte(isPlanted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.PlantedBy);
        dest.writeInt(this.PlantedAmount);
        dest.writeInt(this.RowNumber);
        dest.writeInt(this.OrderinRow);
        dest.writeString(this.PlantedTime);
        dest.writeInt(this.LandID);
        dest.writeInt(this.PositionInList);
        dest.writeInt(this.SurviveAmount);
        dest.writeInt(this.Order);
    }

    public ReceiveFamilyModel() {
    }

    protected ReceiveFamilyModel(Parcel in) {
        this.FamilyCode = in.readString();
        this.SentBy = in.readString();
        this.ReceivedBy = in.readString();
        this.UserReceiver = in.readInt();
        this.ReceivedAmount = in.readInt();
        this.createdTime = in.readString();
        this.updatedTime = in.readString();
        this.MotherCode = in.readString();
        this.FatherCode = in.readString();
        this.isPlanted = in.readByte() != 0;
        this.PlantedBy = in.readInt();
        this.PlantedAmount = in.readInt();
        this.RowNumber = in.readInt();
        this.OrderinRow = in.readInt();
        this.PlantedTime = in.readString();
        this.LandID = in.readInt();
        this.PositionInList = in.readInt();
        this.SurviveAmount = in.readInt();
        this.Order = in.readInt();
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
