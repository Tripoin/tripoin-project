package com.tripoin.phonenetwork.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 12/1/2014.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 *
 * Class Data Transfer Object for Phone and Network Monitor
 */
public class DTOPhoneNetwork implements Parcelable {

    private double latitude;
    private double longitude;
    private float accuracy;
    private String deviceId;
    private String subscriberId;
    private String phoneType;
    private String softwareVersion;
    private String simOperatorName;
    private String simSerial;
    private String mcc;
    private String mnc;
    private String lineNumber;
    private String locality;
    private float batteryLevel;
    private String address;
    private double rxLevel;
    private int rxQuality;
    private double ber;
    private String networkType;
    private int lac;
    private int cellId;
    private String connectionStatus;
    private String serviceState;
    private String ipAddress;
    private String wifiName;
    private String apnName;

    public DTOPhoneNetwork(Parcel in) {
        readFromParcel(in);
    }

    public DTOPhoneNetwork() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRxLevel() {
        return rxLevel;
    }

    public void setRxLevel(double rxLevel) {
        this.rxLevel = rxLevel;
    }

    public int getRxQuality() {
        return rxQuality;
    }

    public void setRxQuality(int rxQuality) {
        this.rxQuality = rxQuality;
    }

    public double getBer() {
        return ber;
    }

    public void setBer(double ber) {
        this.ber = ber;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getApnName() {
        return apnName;
    }

    public void setApnName(String apnName) {
        this.apnName = apnName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeDouble(accuracy);
        dest.writeString(deviceId);
        dest.writeString(subscriberId);
        dest.writeString(phoneType);
        dest.writeString(softwareVersion);
        dest.writeString(simOperatorName);
        dest.writeString(simSerial);
        dest.writeString(mcc);
        dest.writeString(mnc);
        dest.writeString(lineNumber);
        dest.writeString(locality);
        dest.writeFloat(batteryLevel);
        dest.writeString(address);
        dest.writeDouble(rxLevel);
        dest.writeInt(rxQuality);
        dest.writeDouble(ber);
        dest.writeString(networkType);
        dest.writeInt(lac);
        dest.writeInt(cellId);
        dest.writeString(connectionStatus);
        dest.writeString(serviceState);
        dest.writeString(ipAddress);
        dest.writeString(wifiName);
        dest.writeString(apnName);
    }

    private void readFromParcel(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        accuracy = in.readFloat();
        deviceId = in.readString();
        subscriberId = in.readString();
        phoneType = in.readString();
        softwareVersion = in.readString();
        simOperatorName = in.readString();
        simSerial = in.readString();
        mcc = in.readString();
        mnc = in.readString();
        lineNumber = in.readString();
        locality = in.readString();
        batteryLevel = in.readFloat();
        address = in.readString();
        rxLevel = in.readDouble();
        rxQuality = in.readInt();
        ber = in.readDouble();
        networkType = in.readString();
        lac = in.readInt();
        cellId = in.readInt();
        connectionStatus = in.readString();
        serviceState = in.readString();
        ipAddress = in.readString();
        wifiName = in.readString();
        apnName = in.readString();
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new DTOPhoneNetwork();
        }

        @Override
        public Object[] newArray(int size) {
            return new DTOPhoneNetwork[size];
        }
    };

    @Override
    public String toString() {
        return "DTOPhoneNetwork{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", deviceId='" + deviceId + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", simOperatorName='" + simOperatorName + '\'' +
                ", simSerial='" + simSerial + '\'' +
                ", mcc='" + mcc + '\'' +
                ", mnc='" + mnc + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", locality='" + locality + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", address='" + address + '\'' +
                ", rxLevel=" + rxLevel +
                ", rxQuality=" + rxQuality +
                ", ber=" + ber +
                ", networkType='" + networkType + '\'' +
                ", lac=" + lac +
                ", cellId=" + cellId +
                ", connectionStatus='" + connectionStatus + '\'' +
                ", serviceState='" + serviceState + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", wifiName='" + wifiName + '\'' +
                ", apnName='" + apnName + '\'' +
                '}';
    }
}
