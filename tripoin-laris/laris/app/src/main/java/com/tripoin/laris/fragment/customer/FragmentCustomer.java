package com.tripoin.laris.fragment.customer;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentCustomer extends ABaseFragment {

    @BindString(R.string.customer)
    String titleFragmentCustmer;

    @Bind(R.id.toolbarCustomer) Toolbar toolbarCustomer;

    @Override
    public String getFragmentTitle() {
        return titleFragmentCustmer;
    }

    @Override
    public void initWidget() {
        toolbarCustomer.setTitle(getFragmentTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCustomer);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_customer;
    }

}
