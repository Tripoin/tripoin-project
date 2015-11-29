package com.tripoin.tsm_mobile;

import android.content.Context;

import com.tripoin.tripoin_component.app.base.ATRIPOINApplication;

/**
 * Created on 9/28/2015 : 7:50 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TSMApplication extends ATRIPOINApplication{

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
