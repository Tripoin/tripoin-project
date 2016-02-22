package com.tripoin.tripoin_component.ui.handler.login;


import android.app.Dialog;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Toast;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_common.util.GeneralValidation;
import com.tripoin.tripoin_component.R;
import com.tripoin.tripoin_component.ui.activity.impl.NavigatorActivity;
import com.tripoin.tripoin_dao.impl.DAOUser;
import com.tripoin.tripoin_model.ModelUser;
import com.tripoin.tripoin_rest.bgp.base.DialogGenericPost;
import com.tripoin.tripoin_rest.bgp.impl.BGPLogin;
import com.tripoin.tripoin_rest.dto.app.UserLogin;
import com.tripoin.tripoin_rest.dto.response.login.DTOResponseLogin;
import com.tripoin.tripoin_rest.post.IGenericPost;
import com.tripoin.tripoin_rest.post.IPostLogin;
import com.tripoin.tripoin_rest.util.TRIPOINHUDProgressDialog;
import com.tripoin.tripoin_util.network.NetworkConnectivity;

import org.parceler.Parcels;

import retrofit.RetrofitError;

/**
 * Created on 9/30/2015 : 5:42 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
public abstract class ALoginHandler<RESPONSE> implements ILoginHandler, IPostLogin<RESPONSE> {

    private String userName;
    private DAOUser daoUser;
    private ModelUser modelUser;
    private IGenericPost iGenericPost;

    protected ALoginHandler() {
    }

    @Override
    public void doLogin() {
        GeneralValidation generalValidation = new GeneralValidation();
        NetworkConnectivity networkConnectivity = new NetworkConnectivity(getContext());
        if(!generalValidation.isEmptyEditText(getTxtUserName())){
            userName = getTxtUserName().getText().toString().trim();
            if(!generalValidation.isEmptyEditText(getTxtPassword())){
                if(networkConnectivity.isConnected()){
                    daoUser = new DAOUser(getContext());
                    try{
                        modelUser = (ModelUser) daoUser.getAllData().get(0);
                        daoUser.updateEntity(initBaseUser(modelUser));
                    }catch (Exception e){
                        modelUser = new ModelUser();
                        daoUser.insertEntity(initBaseUser(modelUser));
                    }
                    final TRIPOINHUDProgressDialog tripoinhudProgressDialog = TRIPOINHUDProgressDialog.show(
                            ALoginHandler.this.getContext(),
                            ALoginHandler.this.getContext().getResources().getString(R.string.login_pd_message),
                            false,
                            null);
                    iGenericPost = new DialogGenericPost(tripoinhudProgressDialog);
                    new BGPLogin(this) {
                        @Override
                        public Context getContext() {
                            return ALoginHandler.this.getContext();
                        }

                        @Override
                        public Dialog getProgressDialog() {
                            return tripoinhudProgressDialog;
                        }
                    }.execute();
                }else{
                    Toast.makeText(getContext(), R.string.internet_unavailable, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(), R.string.password_empty, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), R.string.user_name_empty, Toast.LENGTH_SHORT).show();
        }
        /*byPassLogin();*/
    }

    private ModelUser initBaseUser(ModelUser modelUser){
        modelUser.setUserName(userName);
        modelUser.setChipperAuth(userName, getTxtPassword().getText().toString().trim());
        return modelUser;
    }

    @Override
    public void onChecked(boolean isChecked) {
        if (!isChecked) {
            getTxtPassword().setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            getTxtPassword().setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void onPostSuccess(RESPONSE response) {
        if(response != null){
            iGenericPost.onPostSuccess(response);
            DTOResponseLogin dtoResponseLogin = (DTOResponseLogin) response;
            Log.i(ApplicationConstant.Log.TRIPOIN_INFO, dtoResponseLogin.toString());
            if(dtoResponseLogin.getResponseCode() == GeneralConstant.BinaryValue.ZERO){
                modelUser = (ModelUser) daoUser.getAllData().get(0);
                modelUser.setLoginStatus(GeneralConstant.BinaryValue.ONE);
                modelUser.setUserName(dtoResponseLogin.getUserDatas().get(0).getUserName());
                modelUser.setUserCode(dtoResponseLogin.getUserDatas().get(0).getRoleData().getCode());
                daoUser.updateEntity(modelUser);

                UserLogin userLogin = new UserLogin();
                userLogin.setUserName(modelUser.getUserName());
                userLogin.setUserCode(modelUser.getUserCode());

                NavigatorActivity navigatorActivity = new NavigatorActivity();
                navigatorActivity.setParameter(getContext());
                navigatorActivity.gotoNextActivity(
                        getSuccessClass(),
                        ApplicationConstant.TransferKeys.USER_LOGIN, Parcels.wrap(userLogin)
                );
            }
        }
    }

    @Override
    public void onPostFailure(RetrofitError retrofitError) {
        iGenericPost.onPostFailure(retrofitError);
        Log.e(ApplicationConstant.Log.TRIPOIN_ERROR, retrofitError.getMessage());
        Toast.makeText(getContext(), retrofitError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void byPassLogin(){
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(ApplicationConstant.Rest.DTO.Request.Login.SAMPLE_USER);
        userLogin.setUserCode(ApplicationConstant.Rest.DTO.Request.Login.SAMPLE_ROLE);

        daoUser = new DAOUser(getContext());
        modelUser = (ModelUser) daoUser.getAllData().get(0);
        modelUser.setLoginStatus(GeneralConstant.BinaryValue.ONE);
        modelUser.setUserName(userLogin.getUserName());
        modelUser.setUserCode(userLogin.getUserCode());
        daoUser.updateEntity(modelUser);

        NavigatorActivity navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(getContext());
        navigatorActivity.gotoNextActivity(
                getSuccessClass(),
                ApplicationConstant.TransferKeys.USER_LOGIN, Parcels.wrap(userLogin)
        );
    }
}
