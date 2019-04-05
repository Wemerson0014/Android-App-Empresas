package br.com.desafio.appempresas.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Enterprise implements Parcelable {

    @SerializedName("enterprise_name")
    private String name;
    private String description;
    private String country;
    private String photo;
    @SerializedName("enterprise_type")
    private EnterpriseType enterpriseType;


    Enterprise(Parcel in) {
        name = in.readString();
        description = in.readString();
        country = in.readString();
        photo = in.readString();
        enterpriseType = in.readParcelable(EnterpriseType.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoto() {
        return photo;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public static final Creator<Enterprise> CREATOR = new Creator<Enterprise>() {
        @Override
        public Enterprise createFromParcel(Parcel in) {
            return new Enterprise(in);
        }

        @Override
        public Enterprise[] newArray(int size) {
            return new Enterprise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       dest.writeString(name);
       dest.writeString(description);
       dest.writeString(country);
       dest.writeString(photo);
       dest.writeParcelable(enterpriseType, flags);
    }
}
