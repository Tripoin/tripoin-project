package com.tripoin.laris.activity;

import android.content.Context;
import android.widget.EditText;

import butterknife.Bind;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.component.ui.handler.login.ALoginHandler;
import com.tripoin.laris.R;

import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created on 9/30/2015 : 5:42 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityLogin extends ABaseActivity{

    @Bind(R.id.txt_username)
    EditText txtUserName;

    @Bind(R.id.txt_password)
    EditText txtPassword;


    private ALoginHandler loginHandler;

    @Override
    public void initWidget() {
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

    @OnCheckedChanged(R.id.chkShowPassword)
    public void onChecked(boolean isChecked){
        loginHandler.onChecked(isChecked);
    }

    @OnClick(R.id.btLogin)
    public void doLogin(){
        loginHandler.doLogin();
    }

    @OnClick(R.id.lblLearnAbout)
    public void learnTsm(){
        NavigatorActivity navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(this);
        navigatorActivity.gotoNextActivity(
                TRIPOINActivityIntro.class,
                GeneralConstant.Punctuation.EMPTY,
                GeneralConstant.Punctuation.EMPTY
        );
    }

}