package org.thailandsbc.cloneplanting.receive;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class ReceiveFamilyModel implements Parcelable {
    public int     ObjectID             ;
    public String  NameTent             ;
    public String  SentBy               ;
    public String  ReceivedBy           ;
    public int     UserReceiver         ;
    public int     ReceivedAmount       ;
    public String  createdTime          ;
    public String  updatedTime          ;
    public String  MotherCode           ;
    public String  FatherCode           ;
    public int     isPlanted            ;
    public int     PlantedBy            ;
    public int     PlantedAmount        ;
    public int     RowNumber            ;
    public int     OrderInRow           ;
    public String  PlantedTime          ;
    public int     LandID               ;
    public int     PositionInList       ;
    public int     SurviveAmount        ;
    public int     Order                ;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ObjectID);
        dest.writeString(this.NameTent);
        dest.writeString(this.SentBy);
        dest.writeString(this.ReceivedBy);
        dest.writeInt(this.UserReceiver);
        dest.writeInt(this.ReceivedAmount);
        dest.writeString(this.createdTime);
        dest.writeString(this.updatedTime);
        dest.writeString(this.MotherCode);
        dest.writeString(this.FatherCode);
        dest.writeInt(this.isPlanted);
        dest.writeInt(this.PlantedBy);
        dest.writeInt(this.PlantedAmount);
        dest.writeInt(this.RowNumber);
        dest.writeInt(this.OrderInRow);
        dest.writeString(this.PlantedTime);
        dest.writeInt(this.LandID);
        dest.writeInt(this.PositionInList);
        dest.writeInt(this.SurviveAmount);
        dest.writeInt(this.Order);
    }

    public ReceiveFamilyModel() {
    }

    protected ReceiveFamilyModel(Parcel in) {
        this.ObjectID = in.readInt();
        this.NameTent = in.readString();
        this.SentBy = in.readString();
        this.ReceivedBy = in.readString();
        this.UserReceiver = in.readInt();
        this.ReceivedAmount = in.readInt();
        this.createdTime = in.readString();
        this.updatedTime = in.readString();
        this.MotherCode = in.readString();
        this.FatherCode = in.readString();
        this.isPlanted = in.readInt();
        this.PlantedBy = in.readInt();
        this.PlantedAmount = in.readInt();
        this.RowNumber = in.readInt();
        this.OrderInRow = in.readInt();
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

    public String getNameTent() {
        return NameTent;
    }

    public void setNameTent(String nameTent) {
        NameTent = nameTent;
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

    public int getIsPlanted() {
        return isPlanted;
    }

    public void setIsPlanted(int isPlanted) {
        this.isPlanted = isPlanted;
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

    public int getOrderInRow() {
        return OrderInRow;
    }

    public void setOrderInRow(int orderInRow) {
        OrderInRow = orderInRow;
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

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }
}
