package com.tripoin.phonenetwork;

import com.tripoin.phonenetwork.listener.TRIPOINPhoneStateListener;

/**
 * Created on 6/9/2015 : 2:01 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class BatchPhoneNetworkParam {

    private PhoneNetwork phoneNetwork;
    private TRIPOINPhoneStateListener tripoinPhoneStateListener;

    public PhoneNetwork getPhoneNetwork() {
        return phoneNetwork;
    }

    public void setPhoneNetwork(PhoneNetwork phoneNetwork) {
        this.phoneNetwork = phoneNetwork;
    }

    public TRIPOINPhoneStateListener getTripoinPhoneStateListener() {
        return tripoinPhoneStateListener;
    }

    public void setTripoinPhoneStateListener(TRIPOINPhoneStateListener tripoinPhoneStateListener) {
        this.tripoinPhoneStateListener = tripoinPhoneStateListener;
    }
}
