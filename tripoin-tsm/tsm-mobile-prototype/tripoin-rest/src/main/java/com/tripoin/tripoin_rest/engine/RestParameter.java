package com.tripoin.tripoin_rest.engine;

import android.content.Context;

import org.parceler.Parcel;

/**
 * Created on 9/26/2015 : 11:08 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class RestParameter {

    private Context context;
    private String chipperAuth;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getChipperAuth() {
        return chipperAuth;
    }

    public void setChipperAuth(String chipperAuth) {
        this.chipperAuth = chipperAuth;
    }

    @Override
    public String toString() {
        return "RestParameter{" +
                "context=" + context +
                ", chipperAuth='" + chipperAuth + '\'' +
                '}';
    }
}
