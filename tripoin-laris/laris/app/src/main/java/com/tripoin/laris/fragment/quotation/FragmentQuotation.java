package com.tripoin.laris.fragment.quotation;

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
public class FragmentQuotation extends ABaseFragment {

    @BindString(R.string.quotation)
    String titleFragmentQuotation;

    @Bind(R.id.toolbarQuotation) Toolbar toolbarCallQuotation;

    @Override
    public String getFragmentTitle() {
        return titleFragmentQuotation;
    }

    @Override
    public void initWidget() {
        toolbarCallQuotation.setTitle(getFragmentTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCallQuotation);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_quotation;
    }

}
