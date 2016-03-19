package com.tripoin.laris;

import android.content.Context;

import com.tripoin.component.app.base.ATRIPOINApplication;

/**
 * <p>
 *     An Application Context
 * </p>
 * Created on 9/28/2015 : 7:50 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class LARISApplication extends ATRIPOINApplication {

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}
