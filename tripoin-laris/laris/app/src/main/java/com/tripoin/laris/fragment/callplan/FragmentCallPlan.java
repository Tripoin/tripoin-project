package com.tripoin.laris.fragment.callplan;

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
public class FragmentCallPlan extends ABaseFragment {

    @BindString(R.string.call_plan) String titleFragmentCallPlan;

    @BindString(R.string.app_name) String titleAppName;

    @Bind(R.id.toolbarLCallPlan) Toolbar toolbarCallPlan;

    @Override
    public String getFragmentTitle() {
        return titleAppName;
    }

    @Override
    public void initWidget() {
        toolbarCallPlan.setTitle(getFragmentTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCallPlan);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_call_plan;
    }

}
