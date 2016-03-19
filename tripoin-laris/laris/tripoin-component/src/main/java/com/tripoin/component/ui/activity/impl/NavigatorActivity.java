package com.tripoin.component.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.tripoin.common.component.ITRIPOINComponent;
import com.tripoin.component.ui.activity.INavigationActivity;

import java.io.Serializable;

/**
 * Created on 10/2/2015 : 3:04 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class NavigatorActivity implements INavigationActivity, ITRIPOINComponent<Context> {

    private Context context;

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, String p_ExtraContent) {
        Intent intent = new Intent( getParameter(), p_Clazz);
        intent.putExtra(p_ExtraKey, p_ExtraContent);
        getParameter().startActivity(intent);
    }

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, Serializable p_ExtraContent) {
        Intent intent = new Intent( getParameter(), p_Clazz);
        intent.putExtra(p_ExtraKey, p_ExtraContent);
        getParameter().startActivity(intent);
    }

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, Parcelable p_ExtraContent) {
        Intent intent = new Intent( getParameter(), p_Clazz);
        intent.putExtra(p_ExtraKey, p_ExtraContent);
        getParameter().startActivity(intent);
    }

    @Override
    public void goToMainView(String p_ExtraKey, String p_ExtraContent) {
        /*Not Defined yet*/
    }

    @Override
    public void exitApplication() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getParameter().startActivity(intent);
    }


    @Override
    public void setParameter(Context p_Param) {
        this.context = p_Param;
    }

    @Override
    public Context getParameter() {
        return context;
    }
}
