package com.tripoin.phonenetwork.telephony.network;

import android.telephony.NeighboringCellInfo;

import java.util.List;

/**
 * Created on 5/29/2015 : 3:34 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * Interface for Telephony feature with customized output based on display Activity requirements
 */
public interface INetworkInformation {
    /**
     * This method is used to get MCC and MNC from SIM operator used by device
     * @return String
     */
    public String getNetworkOperator();

    /**
     * This method is used to get Local Area Code from SIM used by device
     * @return int
     */
    public int getGsmCellLocationLac();

    /**
     * This method is used to get Cell ID from SIM used by device
     * @return int
     */
    public int getGsmCellLocationId();

    /**
     * This method is used to get Neighboring Cell from SIM used by device
     * @return List<NeighboringCellInfo>
     */
    public List<NeighboringCellInfo> getNeighboringCellInfos();

    /**
     * This method is used to get device ID
     * @return String
     */
    public String getDeviceId();

    /**
     * This method is used to get IMSI from SIM used by device
     * @return String
     */
    public String getSubscriberId();

    /**
     * This method is used to get version of operating system used by device
     * @return String
     */
    public String getSoftwareVersion();

    /**
     * This method is used to get SIM ISO country code from SIM used by device
     * @return String
     */
    public String getSIMCountryISO();

    /**
     * This method is used to get operator name from SIM used by device
     * @return String
     */
    public String getSIMOperatorName();

    /**
     * This method is used to get SIM serial number
     * @return String
     */
    public String getSIMSerial();

    /**
     * This method is used to get network country ISO
     * @return String
     */
    public String getNetworkCountryISO();

    /**
     * This method is used to get phone number from SIM used by device
     * @return String
     */
    public String getLineNumber();

    /**
     * This method is used to get Mobile Country Code number ( INA = 510 ) from SIM used by device
     * @return String
     */
    public String getMcc();

    /**
     * This method used to get Mobile Network Code ( TELKOMSEL = 10 ) from SIM used by device
     * @return String
     */
    public String getMnc();

    /**
     * This method is used to get network type which is available from SIM used by device
     * @return String
     */
    public String getNetworkType();

    /**
     * This method is used to get type + manufacture + build version from SIM used by device
     * @return String
     */
    public String getPhoneType();

    /**
     * This method is used to get signal strength ( RSSI ) from SIM used by device
     * @return int[]
     */
    public int[] getSignalStrengthGsm();

    /**
     * Get Connectivity status whether connected or not
     * @return String
     */
    public String getConnectivityStatus();

    /**
     * Get Ip Address from Mobile Data
     * @return String
     */
    public String getMobileIpAddress();

}
