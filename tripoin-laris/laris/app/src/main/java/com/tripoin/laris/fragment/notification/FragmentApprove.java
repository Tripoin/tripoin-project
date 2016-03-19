package com.tripoin.laris.fragment.notification;

import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

import butterknife.BindString;

/**
 * Created on 3/5/2016 : 3:33 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentApprove extends ABaseFragment {

    @BindString(R.string.approve) String strApprove;

    @Override
    public String getFragmentTitle() {
        return strApprove;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_approve;
    }
}
