package com.tripoin.tsm_mobile.fragment.customerdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tripoin_component.ui.fragment.impl.NavigatorFragment;
import com.tripoin.tripoin_component.ui.util.TextViewManipulator;
import com.tripoin.tsm_mobile.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentInputCustomer1 extends ANavigationDrawerFragment {

    @InjectView(R.id.txtCustomerName)
    EditText txtCustomerName;

    @InjectView(R.id.txtContactPerson)
    EditText txtContactPerson;

    @InjectView(R.id.txtPhoneNumber)
    EditText txtPhoneNumber;

    @InjectView(R.id.txtAddress)
    EditText txtAddress;


    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.INPUT_CUSTOMER;
    }

    @Override
    public void initWidget() {
        txtCustomerName.setText("PT. Besmindo Materi Sewatama");
        txtContactPerson.setText("Bp. Saido");
        txtPhoneNumber.setText("0812345670");
        txtAddress.setText("Tangerang Selatan");
        TextViewManipulator textViewManipulator = new TextViewManipulator();
        textViewManipulator.setEnableTextView(new TextView[]{txtCustomerName, txtContactPerson, txtPhoneNumber, txtAddress}, false);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_input_customer1;
    }

    @OnClick(R.id.btNext)
    public void onClickNext(){
        NavigatorFragment navigatorFragment = new NavigatorFragment() {
            @Override
            public FragmentActivity getFragmentActivity() {
                return getActivity();
            }
        };
        FragmentInputCustomer2 fragmentInputCustomer2 = new FragmentInputCustomer2();
        navigatorFragment.gotoNextFragment(R.id.container, fragmentInputCustomer2);
    }

}
