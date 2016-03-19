package com.tripoin.phone.network.telephony.network;

import android.telephony.NeighboringCellInfo;

import java.util.List;

/**
 * <p>
 *     Interface for Telephony feature with customized output based on display Activity requirements
 * </p>
 *
 * Created on 5/29/2015 : 3:34 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface INetworkInformation {
    /**
     * <p>
     *     This method is used to get MCC and MNC from SIM operator used by device
     * </p>
     *
     * @return String
     */
    String getNetworkOperator();

    /**
     * <p>
     *     This method is used to get Local Area Code from SIM used by device
     * </p>
     * @return int
     */
    int getGsmCellLocationLac();

    /**
     * <p>
     *     This method is used to get Cell ID from SIM used by device
     * </p>
     * @return int
     */
    int getGsmCellLocationId();

    /**
     * <p>
     *     This method is used to get Neighboring Cell from SIM used by device
     * </p>
     *
     * @return List<NeighboringCellInfo>
     */
    List<NeighboringCellInfo> getNeighboringCellInfos();

    /**
     * <p>
     *     This method is used to get device ID
     * </p>
     *
     * @return String
     */
    String getDeviceId();

    /**
     * <p>
     *     This method is used to get IMSI from SIM used by device
     * </p>
     *
     * @return String
     */
    String getSubscriberId();

    /**
     * <p>
     *     This method is used to get version of operating system used by device
     * </p>
     *
     * @return String
     */
    String getSoftwareVersion();

    /**
     * <p>
     *     This method is used to get SIM ISO country code from SIM used by device
     * </p>
     *
     * @return String
     */
    String getSIMCountryISO();

    /**
     * <p>
     *     This method is used to get operator name from SIM used by device
     * </p>
     *
     * @return String
     */
    String getSIMOperatorName();

    /**
     * <p>
     *     This method is used to get SIM serial number
     * </p>
     *
     * @return String
     */
    String getSIMSerial();

    /**
     * <p>
     *     This method is used to get network country ISO
     * </p>
     *
     * @return String
     */
    String getNetworkCountryISO();

    /**
     * <p>
     *     This method is used to get phone number from SIM used by device
     * </p>
     *
     * @return String
     */
    String getLineNumber();

    /**
     * <p>
     *     This method is used to get Mobile Country Code number ( INA = 510 ) from SIM used by device
     * </p>
     *
     * @return String
     */
    String getMcc();

    /**
     * <p>
     *     This method used to get Mobile Network Code ( TELKOMSEL = 10 ) from SIM used by device
     * </p>
     *
     * @return String
     */
    String getMnc();

    /**
     * <p>
     *     This method is used to get network type which is available from SIM used by device
     * </p>
     * @return String
     */
    String getNetworkType();

    /**
     * <p>
     *     This method is used to get type + manufacture + build version from SIM used by device
     * </p>
     *
     * @return String
     */
    String getPhoneType();

    /**
     * <p>
     *     This method is used to get signal strength ( RSSI ) from SIM used by device
     * </p>
     * @return int[]
     */
    int[] getSignalStrengthGsm();

    /**
     * <p>
     *     Get Connectivity status whether connected or not
     * </p>
     * @return String
     */
    String getConnectivityStatus();

    /**
     * <p>
     *     Get Ip Address from Mobile Data
     * </p>
     *
     * @return String
     */
    String getMobileIpAddress();

}
