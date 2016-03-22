package com.tripoin.laris.fragment.historytransaction;

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
public class FragmentHistoryTransaction extends ABaseFragment {

    @BindString(R.string.history_transaction)
    String titleFragmentHistoryTransaction;

    @Bind(R.id.toolbarHistoryTransaction) Toolbar toolbarCallQuotation;

    @Override
    public String getFragmentTitle() {
        return titleFragmentHistoryTransaction;
    }

    @Override
    public void initWidget() {
        toolbarCallQuotation.setTitle(GeneralConstant.Punctuation.EMPTY);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCallQuotation);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_history_transaction;
    }

}
