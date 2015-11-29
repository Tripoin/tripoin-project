package com.tripoin.tripoin_component.ui.activity;

import android.content.Context;
import android.os.Parcelable;

import com.tripoin.tripoin_common.component.ITRIPOINComponent;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 12:48 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 *
 * @param <TARGET_CLASS>
 */
public interface INavigationActivity<TARGET_CLASS> extends INavigation {

    /**
     * This method is used to initiate next Activity from current active Activity
     * @param clazz Class
     * @param extraKey String
     * @param extraContent String
     */
    public void gotoNextActivity(Class<TARGET_CLASS> clazz, String extraKey, String extraContent);

    /**
     * This method is used to initiate next Activity from current active Activity
     * @param clazz Class
     * @param extraKey String
     * @param extraContent Serializable
     */
    public void gotoNextActivity(Class<TARGET_CLASS> clazz, String extraKey, Serializable extraContent);


    /**
     * This method is used to initiate next Activity from current active Activity
     * @param clazz Class
     * @param extraKey String
     * @param extraContent Parcel
     */
    public void gotoNextActivity(Class<TARGET_CLASS> clazz, String extraKey, Parcelable extraContent);

}
