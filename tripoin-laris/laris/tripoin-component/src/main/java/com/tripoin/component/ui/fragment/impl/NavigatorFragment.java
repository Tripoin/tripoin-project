package com.tripoin.component.ui.fragment.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.component.ui.fragment.INavigationFragment;


/**
 * Created on 10/11/2015 : 4:08 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class NavigatorFragment implements INavigationFragment {

    protected FragmentManager mFragmentManager;

    public NavigatorFragment() {
        mFragmentManager = getFragmentActivity().getSupportFragmentManager();
    }

    @Override
    public void gotoNextFragment(int p_ContainerId, Fragment p_FragmentView) {
        mFragmentManager.beginTransaction().replace(p_ContainerId, p_FragmentView).commit();
    }

    @Override
    public void gotoPreviousFragment(int p_ContainerId, Fragment p_FragmentView) {
        mFragmentManager.beginTransaction().replace(p_ContainerId, p_FragmentView).commit();
    }

    @Override
    public void goToMainView(String p_ExtraKey, String p_ExtraContent) {
        Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, "Not implemented yet");
    }

    @Override
    public void exitApplication() {
        Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, "Not implemented yet");
    }

    public abstract FragmentActivity getFragmentActivity();
}
