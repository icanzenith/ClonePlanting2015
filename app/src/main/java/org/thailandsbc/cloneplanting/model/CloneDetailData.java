package org.thailandsbc.cloneplanting.model;

/**
 * Created by Icanzenith on 1/6/2016 AD.
 */
public class CloneDetailData {

    private String NameTent;
    private String Order;
    private String FatherName;
    private String MotherName;
    private String SentAmount;
    private String ReceiveAmount;
    private String PlantedAmount;
    private String SurvivedAmount;
    private String isHasDisease;

    public String getNameTent() {
        return NameTent;
    }

    public void setNameTent(String nameTent) {
        NameTent = nameTent;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public String getSentAmount() {
        return SentAmount;
    }

    public void setSentAmount(String sentAmount) {
        SentAmount = sentAmount;
    }

    public String getReceiveAmount() {
        return ReceiveAmount;
    }

    public void setReceiveAmount(String receiveAmount) {
        ReceiveAmount = receiveAmount;
    }

    public String getPlantedAmount() {
        return PlantedAmount;
    }

    public void setPlantedAmount(String plantedAmount) {
        PlantedAmount = plantedAmount;
    }

    public String getSurvivedAmount() {
        return SurvivedAmount;
    }

    public void setSurvivedAmount(String survivedAmount) {
        SurvivedAmount = survivedAmount;
    }

    public String getIsHasDisease() {
        return isHasDisease;
    }

    public void setIsHasDisease(String isHasDisease) {
        this.isHasDisease = isHasDisease;
    }
}
