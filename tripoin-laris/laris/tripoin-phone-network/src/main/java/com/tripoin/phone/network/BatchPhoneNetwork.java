package com.tripoin.phone.network;

import android.os.Build;

import com.tripoin.common.component.ITRIPOINProcessComponent;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.common.constant.GeneralConstant.Network;
import com.tripoin.common.constant.GeneralConstant.Punctuation;
import com.tripoin.common.util.GeneralConverter;
import com.tripoin.phone.network.dto.DTOPhoneNetwork;


/**
 * Created on 5/28/2015 : 4:10 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class BatchPhoneNetwork implements ITRIPOINProcessComponent<BatchPhoneNetworkParam, DTOPhoneNetwork> {

    private BatchPhoneNetworkParam batchPhoneNetworkParam;
    private DTOPhoneNetwork dtoPhoneNetwork;
    private GeneralConverter generalConverter;

    @Override
    public void process() {
        dtoPhoneNetwork = new DTOPhoneNetwork();
        generalConverter = new GeneralConverter();
        double latitude;
        double longitude;
        try{
            latitude = batchPhoneNetworkParam.getPhoneNetwork().getGpsInformation().getLatitude();
            longitude = batchPhoneNetworkParam.getPhoneNetwork().getGpsInformation().getLongitude();
        }catch (Exception e){
            latitude = -1;
            longitude = -1;
        }
        dtoPhoneNetwork.setLatitude(latitude);
        dtoPhoneNetwork.setLongitude(longitude);

        float accuracy;
        try{
            accuracy = (float) generalConverter.roundingUp(batchPhoneNetworkParam.getPhoneNetwork().getGpsInformation().getGpsAccuracy(), 2);
        }catch (Exception e){
            accuracy = -1;
        }
        dtoPhoneNetwork.setAccuracy(accuracy);

        String locality;
        try{
            locality = batchPhoneNetworkParam.getPhoneNetwork().getGpsInformation().getLocality();
        }catch (Exception e){
            locality = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setLocality(locality);

        String address;
        try{
            address = generalConverter.removeLastChar(batchPhoneNetworkParam.getPhoneNetwork().getGpsInformation().getAddressLine());
        }catch (Exception e){
            address = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setAddress(address);

        String deviceId;
        try{
            deviceId = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getDeviceId();
        }catch (Exception e){
            deviceId = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setDeviceId(deviceId);

        String simOperatorName;
        try{
            simOperatorName = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getSIMOperatorName();
        }catch (Exception e){
            simOperatorName = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setSimOperatorName(simOperatorName);

        String simSerial;
        try{
            simSerial = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getSIMSerial();
        }catch (Exception e){
            simSerial = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setSimSerial(simSerial);

        String subscriberId;
        try{
            subscriberId = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getSubscriberId();
        }catch (Exception e){
            subscriberId = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setSubscriberId(subscriberId);

        String mcc;
        try{
            mcc = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getMcc();
        }catch (Exception e){
            mcc = "-1";
        }
        dtoPhoneNetwork.setMcc(mcc);

        String mnc;
        try{
            mnc = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getMnc();
        }catch (Exception e){
            mnc = "-1";
        }
        dtoPhoneNetwork.setMnc(mnc);

        String lineNumber;
        try{
            lineNumber = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getLineNumber();
        }catch (Exception e){
            lineNumber = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setLineNumber(lineNumber);

        dtoPhoneNetwork.setPhoneType(batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getPhoneType());
        dtoPhoneNetwork.setSoftwareVersion(batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getSoftwareVersion());
        dtoPhoneNetwork.setBatteryLevel(batchPhoneNetworkParam.getPhoneNetwork().getBatteryInformation().getBatteryLevel());

        int ber;
        try {
            ber = batchPhoneNetworkParam.getTripoinPhoneStateListener().getGsmBer();
        }catch (Exception e){
            ber = 0;
        }
        dtoPhoneNetwork.setBer(ber);

        String connectionStatus;
        try{
            connectionStatus = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getConnectivityStatus();
        }catch (Exception e){
            connectionStatus = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setConnectionStatus(connectionStatus);

        String ipAddress;
        if(dtoPhoneNetwork.getConnectionStatus().equals(Network.MOBILE_DATA_ENABLED)){
            dtoPhoneNetwork.setWifiName(Punctuation.HYPHEN);
            dtoPhoneNetwork.setNetworkType(batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getNetworkType());
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                dtoPhoneNetwork.setRxLevel(batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getSignalStrengthGsm()[0]);
            } else {
                dtoPhoneNetwork.setRxLevel(batchPhoneNetworkParam.getTripoinPhoneStateListener().getGsmBer());
            }
            try{
                ipAddress = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getMobileIpAddress();
            }catch ( Exception e ){
                ipAddress = Punctuation.HYPHEN;
            }
        }else if(dtoPhoneNetwork.getConnectionStatus().equals(Network.WIFI_ENABLED)){
            dtoPhoneNetwork.setWifiName(generalConverter.removeSpecialChar(batchPhoneNetworkParam.getPhoneNetwork().getWifiInformation().getWifiName()));
            dtoPhoneNetwork.setNetworkType(Network.WIFI);
            dtoPhoneNetwork.setRxLevel(batchPhoneNetworkParam.getPhoneNetwork().getWifiInformation().getWifiSignalStrength());
            try{
                ipAddress = batchPhoneNetworkParam.getPhoneNetwork().getWifiInformation().getWifiIPAddress();
            }catch ( Exception e ){
                ipAddress = Punctuation.HYPHEN;
            }
        }else{
            dtoPhoneNetwork.setWifiName(Punctuation.HYPHEN);
            dtoPhoneNetwork.setNetworkType(Punctuation.HYPHEN);
            dtoPhoneNetwork.setRxLevel(-1.0D);
            try{
                ipAddress = batchPhoneNetworkParam.getPhoneNetwork().getWifiInformation().getWifiIPAddress();
            }catch ( Exception e ){
                ipAddress = Punctuation.HYPHEN;
            }
        }

        int lac;
        try{
            lac = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getGsmCellLocationLac();
        }catch (Exception e){
            lac = -1;
        }
        dtoPhoneNetwork.setLac(lac);

        int cellId;
        try{
            cellId = batchPhoneNetworkParam.getPhoneNetwork().getNetworkInformation().getGsmCellLocationId();
        }catch (Exception e){
            cellId = -1;
        }
        dtoPhoneNetwork.setCellId(cellId);

        dtoPhoneNetwork.setIpAddress(ipAddress);
        dtoPhoneNetwork.setRxQuality(Integer.parseInt(generalConverter.berToSignalQuality(ber)));
        String phoneState = batchPhoneNetworkParam.getTripoinPhoneStateListener().getPhoneState();
        if(phoneState == null){
            phoneState = GeneralConstant.PhoneState.PHONE_STATE_IDLE;
        }
        dtoPhoneNetwork.setServiceState(phoneState);

        String apnName;
        try {
            apnName = batchPhoneNetworkParam.getPhoneNetwork().getApnInformation().getAPNName();
            if( apnName.isEmpty() || apnName.length()<0 ){
                apnName = Punctuation.HYPHEN;

            }
        }catch (Exception e){
            apnName = Punctuation.HYPHEN;
        }
        dtoPhoneNetwork.setApnName(apnName);
    }

    @Override
    public void setTestResult(DTOPhoneNetwork _result) {
        dtoPhoneNetwork = _result;
    }

    @Override
    public DTOPhoneNetwork getTestResult() {
        return dtoPhoneNetwork;
    }

    @Override
    public void setParameter(BatchPhoneNetworkParam p_Param) {
        this.batchPhoneNetworkParam = p_Param;
    }

    @Override
    public BatchPhoneNetworkParam getParameter() {
        return batchPhoneNetworkParam;
    }
}
