package com.tripoin.dto.request;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created on 4/3/2016 : 1:57 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Parcel
public class DTORequestSample {

    @SerializedName("data")
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DTORequestSample{" +
                "data='" + data + '\'' +
                '}';
    }
}

