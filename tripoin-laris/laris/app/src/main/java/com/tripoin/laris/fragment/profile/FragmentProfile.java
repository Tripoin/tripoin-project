package com.tripoin.laris.fragment.profile;

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
public class FragmentProfile extends ABaseFragment {

    @BindString(R.string.profile) String titleFragmentProfile;

    @Bind(R.id.toolbarProfile) Toolbar toolbarProfile;

    @Override
    public String getFragmentTitle() {
        return titleFragmentProfile;
    }

    @Override
    public void initWidget() {
        toolbarProfile.setTitle(GeneralConstant.Punctuation.EMPTY);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarProfile);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_profile;
    }

}
