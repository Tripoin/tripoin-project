package com.tripoin.tripoin_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;

/**
 * Created on 9/27/2015 : 6:25 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TRIPOINBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName component = new ComponentName(context.getPackageName(), TRIPOINIntentService.class.getName());
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, context.getResources().getString(R.string.start_broadcast));

        startWakefulService(context, intent.setComponent(component));
    }
}
