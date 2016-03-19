package com.tripoin.phone.network.telephony.wifi;

/**
 * <p>
 *     Retrieve WIFI information that is connected to device
 * </p>
 * Created on 5/29/2015 : 2:52 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IWifiInformation {

    String getWifiName();

    int getWifiSignalStrength();

    String getWifiIPAddress();

}
