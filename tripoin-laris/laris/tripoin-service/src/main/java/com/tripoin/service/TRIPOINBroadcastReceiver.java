package com.tripoin.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.tripoin.common.constant.ApplicationConstant.LogTag;


/**
 *
 * Created on 9/27/2015 : 6:25 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TRIPOINBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context p_Context, Intent p_Intent) {
        ComponentName component = new ComponentName(p_Context.getPackageName(), FIntentService.class.getName());
        Log.i(LogTag.TRIPOIN_INFO, p_Context.getResources().getString(R.string.start_broadcast));

        startWakefulService(p_Context, p_Intent.setComponent(component));
    }
}
