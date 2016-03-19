package com.tripoin.phone.network.telephony.APN.impl;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.tripoin.phone.network.telephony.APN.IAPNInformation;

/**
 * Created on 5/29/2015 : 3:39 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class APNInformationImpl implements IAPNInformation {

    private Context context;

    public APNInformationImpl(Context context) {
        this.context = context;
    }

    @Override
    public String getAPNName() {
        String name = "";
        try {
            Uri contentUri = Uri.parse("content://telephony/carriers/preferapn");
            Cursor cursor =  context.getContentResolver().query(contentUri, new String[]{"name"}, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    name = cursor.getString(0);
                }
            }
        } catch (Exception e){
            name = "-";
        }
        return name;

    }
}
