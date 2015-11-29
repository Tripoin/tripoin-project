package com.tripoin.tripoin_component.ui.fragment.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.R;
import com.tripoin.tripoin_component.ui.fragment.INavigationFragment;

/**
 * Created on 10/11/2015 : 4:08 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class NavigatorFragment implements INavigationFragment {

    protected FragmentManager mFragmentManager;

    public NavigatorFragment() {
        mFragmentManager = getFragmentActivity().getSupportFragmentManager();
    }

    @Override
    public void gotoNextFragment(int containerId, Fragment fragmentView) {
        mFragmentManager.beginTransaction().replace(containerId, fragmentView).commit();
    }

    @Override
    public void gotoPreviousFragment(int containerId, Fragment fragmentView) {
        mFragmentManager.beginTransaction().replace(R.id.container, fragmentView).commit();
    }

    @Override
    public void goToMainView(String extraKey, String extraContent) {
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Not implemented yet");
    }

    @Override
    public void exitApplication() {
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Not implemented yet");
    }

    public abstract FragmentActivity getFragmentActivity();
}
