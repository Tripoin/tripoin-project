package com.tripoin.laris.fragment.notification;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentNotification extends ABaseFragment {

    @BindString(R.string.notification) String titleFragmentNotification;
    @BindString(R.string.reminder) String strReminder;
    @BindString(R.string.approve) String strApprove;

    @Bind(R.id.toolbarNotification) Toolbar toolbar;
    @Bind(R.id.tabsNotification) TabLayout tabLayout;
    @Bind(R.id.viewpagerNotification) ViewPager viewPager;

    @Override
    public String getFragmentTitle() {
        return titleFragmentNotification;
    }

    @Override
    public void initWidget() {
        setupToolBar();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentReminder(), strReminder);
        adapter.addFragment(new FragmentApprove(), strApprove);
        viewPager.setAdapter(adapter);
    }

    private void setupToolBar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_notification;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
