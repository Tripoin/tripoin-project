package com.tripoin.tsm_mobile.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.activity.impl.HomeBaseActivity;
import com.tripoin.tripoin_component.ui.fragment.impl.MainFragment;
import com.tripoin.tripoin_component.ui.handler.logut.ALogoutHandler;
import com.tripoin.tripoin_rest.dto.app.UserLogin;
import com.tripoin.tsm_mobile.fragment.about.FragmentAbout;
import com.tripoin.tsm_mobile.fragment.callplan.FragmentCallPlan;
import com.tripoin.tsm_mobile.fragment.callreport.FragmentCallReportList;
import com.tripoin.tsm_mobile.fragment.customerdata.FragmentCustomerList;
import com.tripoin.tsm_mobile.fragment.personalinfo.FragmentPersonalInfo;
import com.tripoin.tsm_mobile.fragment.quotation.FragmentQuotation;

import org.parceler.Parcels;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;

/**
 * Created on 9/29/2015 : 12:33 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityHome extends HomeBaseActivity {

    ALogoutHandler logoutHandler;

    @Override
    protected void onResume() {
        super.onResume();
        logoutHandler.detectLoginStatus();
    }

    @Override
    public void onInt(Bundle bundle) {
        logoutHandler = new ALogoutHandler() {
            @Override
            public Context getContext() {
                return ActivityHome.this;
            }

            @Override
            public Class getLoginClass() {
                return ActivitySplashScreen.class;
            }
        };

        UserLogin userLogin = Parcels.unwrap(
                getIntent().getParcelableExtra(ApplicationConstant.TransferKeys.USER_LOGIN)
        );
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Success Login " + userLogin.toString());

        if(userLogin != null){
            if(userLogin.getUserName() != null){
                this.userName.setText(userLogin.getUserName());
            }
            if(userLogin.getUserCode() != null){
                this.userEmail.setText(userLogin.getUserCode());
            }
        }

        this.userPhoto.setImageResource(com.tripoin.tripoin_component.R.mipmap.ic_no_user);
        this.userBackground.setImageResource(com.tripoin.tripoin_component.R.drawable.ic_user_background_first);

        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.personal_info), com.tripoin.tripoin_component.R.drawable.ic_account_18dp);
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.customer_data), com.tripoin.tripoin_component.R.drawable.ic_perm_contact_cal_18dp);
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.call_plan), com.tripoin.tripoin_component.R.drawable.ic_assignment_18dp);
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.call_report), com.tripoin.tripoin_component.R.drawable.ic_assessment_18dp);
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.quotation), com.tripoin.tripoin_component.R.drawable.ic_description_18dp);
        mHelpLiveo.add(getString(com.tripoin.tripoin_component.R.string.about), com.tripoin.tripoin_component.R.drawable.ic_info_18dp);

        with(this)
                .startingPosition(0)
                .addAllHelpItem(mHelpLiveo.getHelp())
                .footerItem(com.tripoin.tripoin_component.R.string.log_out, com.tripoin.tripoin_component.R.drawable.ic_exit_to_app_18dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                mFragment = new FragmentPersonalInfo();
                break;
            case 1:
                mFragment = new FragmentCustomerList();
                break;
            case 2:
                mFragment = new FragmentCallPlan();
                break;
            case 3:
                mFragment = new FragmentCallReportList();
                break;
            case 4:
                mFragment = new FragmentQuotation();
                break;
            case 5:
                mFragment = new FragmentAbout();
                break;

            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(com.tripoin.tripoin_component.R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
            Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "On prepare option :menu "+menu.toString()+" pos "+position+" visible "+visible);
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Click footer");
            closeDrawer();
            logoutHandler.doLogout();
        }
    };
}
