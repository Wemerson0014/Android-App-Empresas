package br.com.desafio.appempresas.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Header implements Parcelable {

    private String uid;
    private String accessToken;
    private String client;

    public Header(String uid, String accessToken, String client) {
        this.uid = uid;
        this.accessToken = accessToken;
        this.client = client;
    }

    public String getUid() {
        return uid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClient() {
        return client;
    }

    Header(Parcel in) {
        uid = in.readString();
        accessToken = in.readString();
        client = in.readString();
    }

    public static final Creator<Header> CREATOR = new Creator<Header>() {
        @Override
        public Header createFromParcel(Parcel in) {
            return new Header(in);
        }

        @Override
        public Header[] newArray(int size) {
            return new Header[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(accessToken);
        dest.writeString(client);
    }
}
