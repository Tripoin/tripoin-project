package com.tripoin.laris.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.component.ui.fragment.impl.NavigatorFragment;
import com.tripoin.dao.DAOComponent;
import com.tripoin.laris.R;
import com.tripoin.laris.fragment.historytransaction.FragmentHistoryTransaction;
import com.tripoin.laris.fragment.home.FragmentHome;
import com.tripoin.laris.fragment.notification.FragmentNotification;
import com.tripoin.laris.fragment.paymentcart.FragmentPaymentCart;
import com.tripoin.laris.fragment.profile.FragmentProfile;
import com.tripoin.util.network.NetworkComponent;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created on 2/21/2016 : 7:55 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityHomeBottomBar extends ABaseActivity implements CompoundButton.OnCheckedChangeListener{

    @Bind(R.id.btHome)  RadioButton btHome;
    @Bind(R.id.btPaymentCart) RadioButton btPaymentCart;
    @Bind(R.id.btnHistoryTransaction) RadioButton btQuotation;
    @Bind(R.id.btNotification) RadioButton btNotification;
    @Bind(R.id.btProfile)  RadioButton btProfile;

    @BindString(R.string.payment_cart) String strPaymentCart;
    @BindString(R.string.history_transaction) String strHistoryTransaction;
    @BindString(R.string.notification) String strNotification;
    @BindString(R.string.home) String strHome;
    @BindString(R.string.profile) String strProfile;

    private Fragment fragmentView = null;
    private NavigatorFragment navigatorFragment;
    private int containerId = R.id.content_frame;

    //NetworkConnectivity networkConnectivity;

    @Override
    public void initWidget() {
        navigatorFragment = new NavigatorFragment() {
            @Override
            public FragmentActivity getFragmentActivity() {
                return ActivityHomeBottomBar.this;
            }
        };

        btHome.setChecked(true);
        fragmentView = new FragmentHome();
        navigatorFragment.gotoNextFragment(containerId, fragmentView);

        btPaymentCart.setOnCheckedChangeListener(this);
        btQuotation.setOnCheckedChangeListener(this);
        btNotification.setOnCheckedChangeListener( this );
        btProfile.setOnCheckedChangeListener(this);
        btHome.setOnCheckedChangeListener(this);

        //networkConnectivity = networkComponent.provideNetworkConnectivity();
    }

    @Override
    public void injectDAOComponent(DAOComponent p_DAOComponent) {
        p_DAOComponent.inject(ActivityHomeBottomBar.this.getApplicationContext());
    }

    @Override
    public void injectNetworkComponent(NetworkComponent p_NetworkComponent) {
        p_NetworkComponent.inject(ActivityHomeBottomBar.this.getApplicationContext());
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onCheckedChanged(CompoundButton p_CompoundButton, boolean p_IsChecked) {
        if (p_IsChecked) {
            String textSelector = (String) p_CompoundButton.getText();

            if (textSelector.equals(strPaymentCart)) {
                fragmentView = new FragmentPaymentCart();
            } else if (textSelector.equals(strHistoryTransaction)) {
                fragmentView = new FragmentHistoryTransaction();
            } else if (textSelector.equals(strNotification)) {
                fragmentView = new FragmentNotification();
            } else if (textSelector.equals(strProfile)) {
                fragmentView = new FragmentProfile();
            }else if(textSelector.equals(strHome)) {
                fragmentView = new FragmentHome();
            }

            navigatorFragment.gotoNextFragment(containerId, fragmentView);
        }
    }

}
