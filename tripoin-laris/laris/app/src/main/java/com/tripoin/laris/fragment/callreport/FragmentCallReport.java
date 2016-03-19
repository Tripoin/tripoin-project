package com.tripoin.laris.fragment.callreport;

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
public class FragmentCallReport extends ABaseFragment {

    @BindString(R.string.call_report)
    String titleFragmentCallReport;

    @Bind(R.id.toolbarCallReport) Toolbar toolbarCallReport;

    @Override
    public String getFragmentTitle() {
        return titleFragmentCallReport;
    }

    @Override
    public void initWidget() {
        toolbarCallReport.setTitle(getFragmentTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarCallReport);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_call_report;
    }

}
