package org.thailandsbc.cloneplanting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class WorkPlaceModel implements Parcelable {
    private String WorkPlaceCode;
    private String WorkPlaceFullName;

    public String getWorkPlaceCode() {
        return WorkPlaceCode;
    }

    public void setWorkPlaceCode(String workPlaceCode) {
        WorkPlaceCode = workPlaceCode;
    }

    public String getWorkPlaceFullName() {
        return WorkPlaceFullName;
    }

    public void setWorkPlaceFullName(String workPlaceFullName) {
        WorkPlaceFullName = workPlaceFullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.WorkPlaceCode);
        dest.writeString(this.WorkPlaceFullName);
    }

    public WorkPlaceModel() {
    }

    protected WorkPlaceModel(Parcel in) {
        this.WorkPlaceCode = in.readString();
        this.WorkPlaceFullName = in.readString();
    }

    public static final Parcelable.Creator<WorkPlaceModel> CREATOR = new Parcelable.Creator<WorkPlaceModel>() {
        public WorkPlaceModel createFromParcel(Parcel source) {
            return new WorkPlaceModel(source);
        }

        public WorkPlaceModel[] newArray(int size) {
            return new WorkPlaceModel[size];
        }
    };
}
