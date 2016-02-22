package com.tripoin.tsm_mobile.fragment.personalinfo;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tripoin_component.ui.util.TextViewManipulator;
import com.tripoin.tripoin_dao.impl.DAOUser;
import com.tripoin.tripoin_model.ModelUser;
import com.tripoin.tsm_mobile.R;

import butterknife.InjectView;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentPersonalInfo extends ANavigationDrawerFragment {

    @InjectView(R.id.imgSalesPersonal)
    ImageView imgSalesPersonal;

    @InjectView(R.id.txtSalesName)
    EditText txtSalesName;

    @InjectView(R.id.txtSalesId)
    EditText txtSalesId;

    @InjectView(R.id.txtBranch)
    EditText txtBranch;

    @InjectView(R.id.txtSupervisor)
    EditText txtSupervisor;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.PERSONAL_INFO;
    }

    @Override
    public void initWidget() {
        this.imgSalesPersonal.setImageResource(com.tripoin.tripoin_component.R.mipmap.ic_no_user);
        DAOUser daoUser = new DAOUser(getActivity());
        ModelUser modelUser = (ModelUser) daoUser.getAllData().get(0);
        txtSalesName.setText(modelUser.getUserName());
        txtSalesId.setText("SK123M");
        txtBranch.setText("KEI");
        txtSupervisor.setText("Muchson");
        TextViewManipulator textViewManipulator = new TextViewManipulator();
        textViewManipulator.setEnableTextView(new TextView[]{txtSalesName, txtSalesId, txtBranch, txtSupervisor}, false);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_personal_info;
    }

    private void setEnabled(boolean enabled){
        txtSalesName.setEnabled(enabled);
        txtSalesId.setEnabled(enabled);
        txtBranch.setEnabled(enabled);
        txtSupervisor.setEnabled(enabled);
    }
}
