package com.tripoin.phone.network.listener;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.tripoin.common.constant.ApplicationConstant.LogTag;
import com.tripoin.common.constant.GeneralConstant.PhoneState;

import java.lang.reflect.Method;


/**
 * <p>
 *      Listen through Phone State Event that occurred when call state changed
 *      @see TRIPOINPhoneStateListener#onCallStateChanged(int, String)
 * </p>
 *
 * Created on 5/29/2015 : 6:06 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TRIPOINPhoneStateListener extends PhoneStateListener{

    private String phoneState;
    private int signalStrengthDbm;
    int gsmBer;

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                phoneState = PhoneState.PHONE_STATE_IDLE;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                phoneState = PhoneState.PHONE_STATE_OFF_HOOK;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                phoneState = PhoneState.PHONE_STATE_RINGING;
                break;
            default:
                phoneState = PhoneState.PHONE_STATE_IDLE;
        }
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        gsmBer = signalStrength.getGsmBitErrorRate();
        int asu = 0;
        if ( signalStrength.isGsm() ){
            asu = signalStrength.getGsmSignalStrength();
            if ( asu<0 || asu>=99 ) {
                Integer value = -1;
                Method m;
                try {
                    m = SignalStrength.class.getMethod("getGsmSignalBar");
                    value = (Integer) m.invoke(signalStrength);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (value == 4) {
                    asu = 18;
                } else if (value == 3) {
                    asu = 9;
                } else if (value == 2) {
                    asu = 3;
                } else{
                    asu = 1;
                }
            }
        }else{
            Log.d(LogTag.TRIPOIN_INFO,"SIGNAL STRENGTH IS NOT GSM");
        }
        signalStrengthDbm =  (-113 + (2 * asu));
    }

    public String getPhoneState() {
        return phoneState;
    }

    public int getGsmBer() {
        return gsmBer;
    }

    public int getSignalStrengthDbm() {
        return signalStrengthDbm;
    }
}
