package com.tripoin.laris.fragment.paymentcart;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentPaymentCart extends ABaseFragment {

    @BindString(R.string.payment_cart)
    String titleFragmentCallReport;

    @Bind(R.id.toolbarPaymentCart) Toolbar toolbarCallReport;

    @Override
    public String getFragmentTitle() {
        return titleFragmentCallReport;
    }

    @Override
    public void initWidget() {
        toolbarCallReport.setTitle(GeneralConstant.Punctuation.EMPTY);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCallReport);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_payment_cart;
    }

}
