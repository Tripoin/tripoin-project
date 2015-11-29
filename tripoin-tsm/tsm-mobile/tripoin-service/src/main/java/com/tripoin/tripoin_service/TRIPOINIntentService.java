package com.tripoin.tripoin_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tripoin.tripoin_common.component.ITRIPOINComponent;
import com.tripoin.tripoin_component.ui.notif.TRIPOINNotifier;

/**
 * Created on 9/28/2015 : 9:17 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <VIEW>
 */
public class TRIPOINIntentService<VIEW> extends IntentService implements ITRIPOINComponent<TRIPOINISParam<VIEW>> {

    public static final int NOTIFICATION_ID = 1;
    private TRIPOINISParam<VIEW> viewParam;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TRIPOINIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        String message = extras.getString("message");
        Log.i("IntelTrackerApp", "Message Received : " + message);
        if(!extras.isEmpty()){
            if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
                Log.d("NOTIF", "NOT GENERATE");
            }else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                Log.d("NOTIF", "NOT GENERATE");
            }else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
                Log.d("NOTIF", "GENERATE");
                new TRIPOINNotifier().generateNotification(this, message, getParameter().getViewClass());
            }
        }
    }

    @Override
    public void setParameter(TRIPOINISParam<VIEW> _param) {
        this.viewParam =_param;
    }

    @Override
    public TRIPOINISParam<VIEW> getParameter() {
        return viewParam;
    }
}
