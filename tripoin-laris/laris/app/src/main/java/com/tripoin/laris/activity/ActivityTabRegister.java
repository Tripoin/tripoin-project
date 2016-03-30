package com.tripoin.laris.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.Toast;

import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.component.ui.view.PagerSlidingTabStrip;
import com.tripoin.laris.R;
import com.tripoin.laris.fragment.FragmentRegisterMerchant;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created on 3/25/2016 : 10:53 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityTabRegister extends ABaseActivity {

    @Bind(R.id.toolbar_tab_media)
    Toolbar toolbar;

    @Bind(R.id.activity_tab_media_tabs)
    PagerSlidingTabStrip tabs;

    @Bind(R.id.activity_tab_media_pager)
    ViewPager pager;

    @BindString(R.string.registration)
    String registrationWord;

    @BindString(R.string.merchant_upper)
    String merchantWord;

    @BindString(R.string.buyer_upper)
    String buyerWord;

    @Override
    public void initWidget() {
        toolbar.setTitle(registrationWord);
        setSupportActionBar(toolbar);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                Toast.makeText(ActivityTabRegister.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_tab_register;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<String> tabNames = new ArrayList<String>() {{
            add(merchantWord);
            add(buyerWord);
        }};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

        @Override
        public int getCount() {
            return tabNames.size();
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new FragmentRegisterMerchant();
            }else{
                return new FragmentRegisterMerchant();
            }
        }
    }
}
