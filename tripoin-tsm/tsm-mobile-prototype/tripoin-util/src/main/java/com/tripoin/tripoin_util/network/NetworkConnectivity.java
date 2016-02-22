package com.tripoin.tripoin_util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created on 6/22/2015 : 9:34 AM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class NetworkConnectivity {
    private Context context;

    public NetworkConnectivity(Context context) {
        this.context = context;
    }

    /**
     * Check network connectivity whether device is connected or not
     * @return boolean
     * true if connected to network
     */
    public boolean isConnected(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
        }
        return enabled;
    }
}
