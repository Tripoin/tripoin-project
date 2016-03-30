package com.tripoin.laris.fragment;


import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

/**
 * Created on 3/25/2016 : 10:56 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentRegisterMerchant extends ABaseFragment {

    @Override
    public String getFragmentTitle() {
        return GeneralConstant.Punctuation.EMPTY;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_register_merchant;
    }
}
