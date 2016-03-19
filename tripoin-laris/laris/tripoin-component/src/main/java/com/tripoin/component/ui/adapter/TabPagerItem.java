package com.tripoin.component.ui.adapter;

import android.support.v4.app.Fragment;

/**
 * <p>
 *     Paging Tab View
 * </p>
 */
public class TabPagerItem {

    private final CharSequence mTitle;
    private final Fragment mFragment;

    public TabPagerItem(CharSequence title, Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
