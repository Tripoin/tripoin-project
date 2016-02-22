package com.tripoin.phonenetwork.battery.impl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.tripoin.phonenetwork.battery.IBatteryInformation;
import com.tripoin.tripoin_common.constant.GeneralConstant;


/**
 * Created on 5/29/2015 : 5:47 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class BatteryInformationImpl implements IBatteryInformation {

    private Intent batteryIntent;

    public BatteryInformationImpl(Context context) {
        if(context != null){
            batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }
    }

    @Override
    public float getBatteryLevel() {
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if(level == -1 || scale == -1) {
            return 50.0f;
        }
        return ((float)level / (float)scale) * 100.0f;
    }

    @Override
    public String getBatteryHealth() {
        int health = batteryIntent.getIntExtra(GeneralConstant.BatteryState.BATTERY_INTENT_HEALTH_EXTRA_KEY, 0);
        String healthString = GeneralConstant.BatteryState.UNKNOWN;
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = GeneralConstant.BatteryState.BATTERY_DEAD;
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = GeneralConstant.BatteryState.BATTERY_GOOD;
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = com.tripoin.tripoin_common.constant.GeneralConstant.BatteryState.BATTERY_OVER_VOLTAGE;
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = GeneralConstant.BatteryState.BATTERY_OVER_HEAT;
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = GeneralConstant.BatteryState.BATTERY_FAILURE;
                break;
        }
        return healthString;
    }
}
