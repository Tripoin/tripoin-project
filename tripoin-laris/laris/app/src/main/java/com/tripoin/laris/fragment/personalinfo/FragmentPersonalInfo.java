package com.tripoin.laris.fragment.personalinfo;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindString;
import com.tripoin.component.ui.fragment.impl.ABaseFragment;
import com.tripoin.component.ui.util.TextViewManipulator;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.laris.R;

import butterknife.Bind;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentPersonalInfo extends ABaseFragment {

    @Bind(R.id.imgSalesPersonal)
    ImageView imgSalesPersonal;

    @Bind(R.id.txtMyName)
    EditText txtMyName;

    @Bind(R.id.txtMyNickName)
    EditText txtMyNickName;

    @Bind(R.id.txtMyMachine)
    EditText txtMyMachine;

    @BindString(R.string.fragment_personal_info_title)
    String titleFragmetPersonalInfo;

    @Override
    public String getFragmentTitle() {
        return titleFragmetPersonalInfo;
    }

    @Override
    public void initWidget() {
        this.imgSalesPersonal.setImageResource(com.tripoin.component.R.mipmap.ic_no_user);
        DAOUser daoUser = new DAOUser(getActivity());
        ModelUser modelUser = (ModelUser) daoUser.getAllData().get(0);
        txtMyName.setText(modelUser.getUserName());
        txtMyNickName.setText("Fauzi");
        txtMyMachine.setText("ABC123");
        TextViewManipulator textViewManipulator = new TextViewManipulator();
        textViewManipulator.setEnableTextView(new TextView[]{txtMyName, txtMyNickName, txtMyMachine}, false);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_personal_info;
    }

}
