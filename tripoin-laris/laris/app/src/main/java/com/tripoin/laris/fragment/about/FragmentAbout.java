package com.tripoin.laris.fragment.about;

import butterknife.BindString;
import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.laris.R;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentAbout extends ABaseFragment {

    @BindString(R.string.about)
    String titleFragmentAbout;

    @Override
    public String getFragmentTitle() {
        return titleFragmentAbout;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_about;
    }

}
