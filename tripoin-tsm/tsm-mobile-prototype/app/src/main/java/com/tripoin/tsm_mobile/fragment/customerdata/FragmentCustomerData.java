package com.tripoin.tsm_mobile.fragment.customerdata;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tsm_mobile.R;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentCustomerData extends ANavigationDrawerFragment {

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.CUSTOMER_DATA;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_customer_data;
    }

}
