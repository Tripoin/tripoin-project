package com.tripoin.laris.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;

import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.component.ui.fragment.impl.NavigatorFragment;
import com.tripoin.laris.R;
import com.tripoin.laris.fragment.callplan.FragmentCallPlan;
import com.tripoin.laris.fragment.callreport.FragmentCallReport;
import com.tripoin.laris.fragment.customer.FragmentCustomer;
import com.tripoin.laris.fragment.notification.FragmentNotification;
import com.tripoin.laris.fragment.profile.FragmentProfile;
import com.tripoin.laris.fragment.quotation.FragmentQuotation;

/**
 * Created on 2/21/2016 : 7:55 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityHomeBottomBar extends ABaseActivity implements CompoundButton.OnCheckedChangeListener{

    @Bind(R.id.btCallReport) RadioButton btCallReport;
    @Bind(R.id.btnQuotation) RadioButton btQuotation;
    @Bind(R.id.btCallPlan)  RadioButton btCallPlan;
    @Bind(R.id.btNotification) RadioButton btNotification;
    @Bind(R.id.btCustomer)  RadioButton btCustomer;
    @Bind(R.id.btProfile)  RadioButton btProfile;

    @BindString(R.string.customer) String strCustomer;
    @BindString(R.string.call_report) String strCallReport;
    @BindString(R.string.quotation) String strQuotation;
    @BindString(R.string.notification) String strNotification;
    @BindString(R.string.call_plan) String strCallPlan;
    @BindString(R.string.profile) String strProfile;

    private Fragment fragmentView = null;
    private NavigatorFragment navigatorFragment;
    private int containerId = R.id.content_frame;

    @Override
    public void initWidget() {
        navigatorFragment = new NavigatorFragment() {
            @Override
            public FragmentActivity getFragmentActivity() {
                return ActivityHomeBottomBar.this;
            }
        };

        btCallPlan.setChecked(true);
        fragmentView = new FragmentCallPlan();
        navigatorFragment.gotoNextFragment(containerId, fragmentView);

        btCallReport.setOnCheckedChangeListener(this);
        btQuotation.setOnCheckedChangeListener(this);
        btCustomer.setOnCheckedChangeListener( this );
        btNotification.setOnCheckedChangeListener( this );
        btProfile.setOnCheckedChangeListener(this);
        btCallPlan.setOnCheckedChangeListener(this);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onCheckedChanged(CompoundButton p_CompoundButton, boolean p_IsChecked) {
        if (p_IsChecked) {
            String textSelector = (String) p_CompoundButton.getText();

            if (textSelector.equals(strCallReport)) {
                fragmentView = new FragmentCallReport();
            } else if (textSelector.equals(strQuotation)) {
                fragmentView = new FragmentQuotation();
            } else if (textSelector.equals(strCustomer)) {
                fragmentView = new FragmentCustomer();
            } else if (textSelector.equals(strNotification)) {
                fragmentView = new FragmentNotification();
            } else if (textSelector.equals(strProfile)) {
                fragmentView = new FragmentProfile();
            }else if(textSelector.equals(strCallPlan)) {
                fragmentView = new FragmentCallPlan();
            }

            navigatorFragment.gotoNextFragment(containerId, fragmentView);
        }
    }

    @OnClick(R.id.fab)
    public void onClickFab(){
        Toast.makeText(this, "Hello I am floating action button", Toast.LENGTH_LONG).show();

        /*Snackbar snackbar = Snackbar.make(ActivityHomeBottomBar.this, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
        snackbar.show();*/
    }
}
