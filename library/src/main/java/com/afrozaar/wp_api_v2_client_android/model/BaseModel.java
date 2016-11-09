package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Base model class for data objects
 *
 * @author Jan-Louis Crafford
 *         Created on 2016/02/10.
 */
public abstract class BaseModel implements Parcelable {

    /**
     * Object id. Represents the unique row id from SQLite database.
     */
    public long rowId = -1;

    public BaseModel() {
    }

    public BaseModel(Parcel in) {
        rowId = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(rowId);
    }
}
