package com.tripoin.phonenetwork.telephony.wifi.impl;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.tripoin.phonenetwork.telephony.wifi.IWifiInformation;
import com.tripoin.tripoin_common.constant.GeneralConstant;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;


/**
 * Created on 5/29/2015 : 2:22 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class WifiInformationImpl implements IWifiInformation {

    private WifiManager wifiManager;
    private WifiInfo wifiInfo;

    public WifiInformationImpl(WifiManager wifiManager, WifiInfo wifiInfo) {
        this.wifiManager = wifiManager;
        this.wifiInfo = wifiInfo;
    }

    @Override
    public String getWifiName(){
        return wifiInfo.getSSID();
    }

    @Override
    public int getWifiSignalStrength(){
        return wifiManager.getConnectionInfo().getRssi();
    }

    @Override
    public String getWifiIPAddress() {
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFI Exception", "Unable to get host address ".concat(ex.toString()));
            ipAddressString = GeneralConstant.Punctuation.HYPHEN;
        }
        return ipAddressString;
    }

}
