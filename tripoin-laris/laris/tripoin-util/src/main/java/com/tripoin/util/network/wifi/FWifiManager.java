package com.tripoin.util.network.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created on 2/24/2016 : 4:14 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FWifiManager implements IFWifiManager{

    private Context context;
    private WifiManager wifiManager;

    public FWifiManager(Context p_Context) {
        this.context = p_Context;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }


    @Override
    public void turnOffWIfi() {
        if(wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(false);
        }
    }

    @Override
    public void turnOnWifi() {
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }
}
