package com.tripoin.laris.fragment.intro;

import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

/**
 * Created on 3/22/2016 : 6:12 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentIntro3 extends ABaseFragment {
    @Override
    public String getFragmentTitle() {
        return GeneralConstant.Punctuation.EMPTY;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_intro_3;
    }
}
