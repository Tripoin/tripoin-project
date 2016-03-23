package com.tripoin.laris.fragment.notification;

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
public class FragmentNotification extends ABaseFragment {

    @BindString(R.string.notification)
    String titleFragmentNotification;

    @Bind(R.id.toolbarNotification)
    Toolbar toolbarNotification;


    @Override
    public String getFragmentTitle() {
        return titleFragmentNotification;
    }

    @Override
    public void initWidget() {
        toolbarNotification.setTitle(GeneralConstant.Punctuation.EMPTY);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarNotification);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_notification;
    }

}
