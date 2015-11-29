package com.tripoin.phonenetwork;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.tripoin.phonenetwork.battery.IBatteryInformation;
import com.tripoin.phonenetwork.battery.impl.BatteryInformationImpl;
import com.tripoin.phonenetwork.gps.IGPSInformation;
import com.tripoin.phonenetwork.gps.impl.GPSInformationImpl;
import com.tripoin.phonenetwork.telephony.APN.IAPNInformation;
import com.tripoin.phonenetwork.telephony.APN.impl.APNInformationImpl;
import com.tripoin.phonenetwork.telephony.network.INetworkInformation;
import com.tripoin.phonenetwork.telephony.network.impl.NetworkInformationImpl;
import com.tripoin.phonenetwork.telephony.wifi.IWifiInformation;
import com.tripoin.phonenetwork.telephony.wifi.impl.WifiInformationImpl;
import com.tripoin.tripoin_common.component.ITRIPOINComponent;


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
    public void setParameter(PhoneNetworkParam _param) {
        phoneNetworkParam = _param;
        initializeDependencies();
    }

    @Override
    public PhoneNetworkParam getParameter() {
        return phoneNetworkParam;
    }
}
