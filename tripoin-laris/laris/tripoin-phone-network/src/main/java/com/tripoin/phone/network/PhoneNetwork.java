package com.tripoin.phone.network;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.tripoin.common.component.ITRIPOINComponent;
import com.tripoin.phone.network.battery.IBatteryInformation;
import com.tripoin.phone.network.battery.impl.BatteryInformationImpl;
import com.tripoin.phone.network.gps.IGPSInformation;
import com.tripoin.phone.network.gps.impl.GPSInformationImpl;
import com.tripoin.phone.network.telephony.APN.IAPNInformation;
import com.tripoin.phone.network.telephony.APN.impl.APNInformationImpl;
import com.tripoin.phone.network.telephony.network.INetworkInformation;
import com.tripoin.phone.network.telephony.network.impl.NetworkInformationImpl;
import com.tripoin.phone.network.telephony.wifi.IWifiInformation;
import com.tripoin.phone.network.telephony.wifi.impl.WifiInformationImpl;


/**
 * Created on 5/29/2015 : 11:48 AM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class PhoneNetwork implements ITRIPOINComponent<PhoneNetworkParam> {

    private WifiInfo wifiInfo;
    private WifiManager wifiManager;
    private LocationManager locationManager;
    private TelephonyManager telephonyManager;
    private ConnectivityManager connectivityManager;

    private IWifiInformation wifiInformation;
    private IGPSInformation gpsInformation;
    private IAPNInformation apnInformation;
    private INetworkInformation networkInformation;
    private IBatteryInformation batteryInformation;
    private PhoneNetworkParam phoneNetworkParam;

    private void initializeDependencies(){
        wifiManager = (WifiManager) phoneNetworkParam.getContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        locationManager = (LocationManager) phoneNetworkParam.getContext().getSystemService(Context.LOCATION_SERVICE);
        telephonyManager = (TelephonyManager) phoneNetworkParam.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        connectivityManager = (ConnectivityManager) phoneNetworkParam.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        wifiInformation = new WifiInformationImpl(wifiManager, wifiInfo);
        gpsInformation = new GPSInformationImpl(locationManager, phoneNetworkParam.getContext());
        apnInformation = new APNInformationImpl(phoneNetworkParam.getContext());
        networkInformation = new NetworkInformationImpl(telephonyManager, connectivityManager);
        batteryInformation = new BatteryInformationImpl(phoneNetworkParam.getContext());
    }

    public IWifiInformation getWifiInformation() {
        return wifiInformation;
    }

    public IGPSInformation getGpsInformation() {
        return gpsInformation;
    }

    public IAPNInformation getApnInformation() {
        return apnInformation;
    }

    public INetworkInformation getNetworkInformation() {
        return networkInformation;
    }

    public IBatteryInformation getBatteryInformation() {
        return batteryInformation;
    }

    @Override
    public void setParameter(PhoneNetworkParam p_Param) {
        phoneNetworkParam = p_Param;
        initializeDependencies();
    }

    @Override
    public PhoneNetworkParam getParameter() {
        return phoneNetworkParam;
    }
}
