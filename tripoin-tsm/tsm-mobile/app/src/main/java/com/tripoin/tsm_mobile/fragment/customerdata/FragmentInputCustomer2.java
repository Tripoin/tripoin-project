package com.tripoin.tsm_mobile.fragment.customerdata;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tripoin_component.ui.util.TextViewManipulator;
import com.tripoin.tsm_mobile.R;

import butterknife.InjectView;


/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentInputCustomer2 extends ANavigationDrawerFragment {

    @InjectView(R.id.spinArea)
    Spinner spinArea;

    @InjectView(R.id.txtNPWP)
    EditText txtNPWP;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.INPUT_CUSTOMER;
    }

    @Override
    public void initWidget() {
        txtNPWP.setText("123456ABC");
        TextViewManipulator textViewManipulator = new TextViewManipulator();
        textViewManipulator.setEnableTextView(new TextView[]{txtNPWP}, false);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_input_customer2;
    }

}
