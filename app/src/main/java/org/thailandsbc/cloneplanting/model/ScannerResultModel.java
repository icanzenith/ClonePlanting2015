package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by Icanzenith on 8/31/15 AD.
 *
 */

public class ScannerResultModel implements Parcelable {

    private String FamilyCode;
    private int Amount;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FamilyCode);
        dest.writeInt(this.Amount);
    }

    public ScannerResultModel() {
    }

    protected ScannerResultModel(Parcel in) {
        this.FamilyCode = in.readString();
        this.Amount = in.readInt();
    }

    public static final Parcelable.Creator<ScannerResultModel> CREATOR = new Parcelable.Creator<ScannerResultModel>() {
        public ScannerResultModel createFromParcel(Parcel source) {
            return new ScannerResultModel(source);
        }

        public ScannerResultModel[] newArray(int size) {
            return new ScannerResultModel[size];
        }
    };


    public String getFamilyCode() {
        return FamilyCode;
    }

    public void setFamilyCode(String familyCode) {
        FamilyCode = familyCode;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
