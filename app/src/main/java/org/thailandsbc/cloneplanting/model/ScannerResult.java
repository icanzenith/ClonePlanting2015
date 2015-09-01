package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class ScannerResult implements Parcelable {

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

    public ScannerResult() {
    }

    protected ScannerResult(Parcel in) {
        this.FamilyCode = in.readString();
        this.Amount = in.readInt();
    }

    public static final Parcelable.Creator<ScannerResult> CREATOR = new Parcelable.Creator<ScannerResult>() {
        public ScannerResult createFromParcel(Parcel source) {
            return new ScannerResult(source);
        }

        public ScannerResult[] newArray(int size) {
            return new ScannerResult[size];
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
