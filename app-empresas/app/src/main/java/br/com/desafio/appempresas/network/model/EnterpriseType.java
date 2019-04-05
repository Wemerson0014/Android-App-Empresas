package br.com.desafio.appempresas.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EnterpriseType implements Parcelable {

    private int id;
    @SerializedName("enterprise_type_name")
    private String enterpriseTypeName;

    EnterpriseType(Parcel in) {
        id = in.readInt();
        enterpriseTypeName = in.readString();
    }

    public String getEnterpriseTypeName() {
        return enterpriseTypeName;
    }

    public static final Creator<EnterpriseType> CREATOR = new Creator<EnterpriseType>() {
        @Override
        public EnterpriseType createFromParcel(Parcel in) {
            return new EnterpriseType(in);
        }

        @Override
        public EnterpriseType[] newArray(int size) {
            return new EnterpriseType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(enterpriseTypeName);
    }
}
