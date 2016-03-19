package com.tripoin.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tripoin.common.component.ITRIPOINComponent;
import com.tripoin.component.ui.notif.TicketingNotifier;

/**
 * Created on 9/28/2015 : 9:17 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <VIEW>
 */
public class FIntentService<VIEW> extends IntentService implements ITRIPOINComponent<FSParam<VIEW>> {

    public static final int NOTIFICATION_ID = 1;
    private FSParam<VIEW> viewParam;

    /**
     * <p>
     *     Creates an IntentService.  Invoked by your subclass's constructor.
     * </p>
     *
     * @param p_Name Used to name the worker thread, important only for debugging.
     */
    public FIntentService(String p_Name) {
        super(p_Name);
    }

    @Override
    protected void onHandleIntent(Intent p_Intent) {
        Bundle extras = p_Intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(p_Intent);
        String message = extras.getString("message");
        Log.i("IntelTrackerApp", "Message Received : " + message);
        if(!extras.isEmpty()){
            if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
                Log.d("NOTIF", "NOT GENERATE");
            }else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                Log.d("NOTIF", "NOT GENERATE");
            }else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
                Log.d("NOTIF", "GENERATE");
                new TicketingNotifier().generateNotification(this, message, getParameter().getViewClass());
            }
        }
    }

    @Override
    public void setParameter(FSParam<VIEW> p_Param) {
        this.viewParam = p_Param;
    }

    @Override
    public FSParam<VIEW> getParameter() {
        return viewParam;
    }
}
