package com.tripoin.tsm_mobile.fragment.callplan;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.dialog.calendar.TRIPOINCalendarDialog;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tsm_mobile.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentCallPlan extends ANavigationDrawerFragment {

    @InjectView(R.id.txtCustomer)
    EditText txtCustomer;

    @InjectView(R.id.spinLocation)
    Spinner spinLocation;

    @InjectView(R.id.txtLocation)
    EditText txtLocation;

    @InjectView(R.id.txtPlanDate)
    EditText txtPlandate;

    @InjectView(R.id.txtObjective)
    EditText txtObjective;

    @InjectView(R.id.btSubmit)
    Button btSubmit;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.CALL_PLAN;
    }

    @Override
    public void initWidget() {
        spinLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    String[] source = getActivity().getResources().getStringArray(R.array.location_res_array);
                    txtLocation.setText(source[position]);
                }catch (Exception e){
                    txtLocation.setText("Mars Exception");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_call_plan;
    }

    @OnClick(R.id.btPlanDate)
    public void chooseDate(){
        new TRIPOINCalendarDialog() {
            @Override
            public void action(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtPlandate.setText(
                        dayOfMonth+" - "+monthOfYear+" - "+year
                );
            }
        }.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

}
