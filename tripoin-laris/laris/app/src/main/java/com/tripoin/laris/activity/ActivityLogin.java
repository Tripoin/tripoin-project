package com.tripoin.laris.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.component.ui.handler.login.ALoginHandler;
import com.tripoin.laris.R;

import java.util.List;

import butterknife.OnClick;

/**
 * Created on 9/30/2015 : 5:42 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityLogin extends ABaseActivity {

    @Bind(R.id.txt_username)
    EditText txtUserName;

    @Bind(R.id.txt_password)
    EditText txtPassword;

    @Bind(R.id.btLogin)
    Button btLogin;

    private ALoginHandler loginHandler;

    @Override
    public void handlingStatusBar() {
        super.handlingStatusBar();


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void initWidget() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        getSupportActionBar().hide();

        txtUserName.setFocusable(true);

        loginHandler = new ALoginHandler() {
            @Override
            public Context getContext() {
                return ActivityLogin.this;
            }

            @Override
            public EditText getTxtUserName() {
                return txtUserName;
            }

            @Override
            public EditText getTxtPassword() {
                return txtPassword;
            }

            @Override
            public Class getSuccessClass() {
                //return ActivityHomeNavigationDrawerMode.class;
                return ActivityHomeBottomBar.class;
            }
        };
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public List<Button> getButtons() {
        buttons.add(btLogin);
        return buttons;
    }

    /*@OnCheckedChanged(R.id.chkShowPassword)
    public void onChecked(boolean isChecked){
        loginHandler.onChecked(isChecked);
    }*/

    @OnClick(R.id.btLogin)
    public void doLogin(){
        loginHandler.doLogin();
    }
    
}