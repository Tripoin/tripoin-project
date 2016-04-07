package com.tripoin.component.ui.handler.login;


import android.app.Dialog;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Toast;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.common.constant.ApplicationConstant.LogTag;
import com.tripoin.common.constant.ApplicationConstant.Rest.DTO.Request.Login;
import com.tripoin.common.constant.ApplicationConstant.TransferKeys;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.common.constant.GeneralConstant.BinaryValue;
import com.tripoin.common.util.GeneralValidation;
import com.tripoin.component.R;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.dao.DAOComponent;
import com.tripoin.dao.DAOModule;
import com.tripoin.dao.DaggerDAOComponent;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.rest.bgp.base.DialogGenericPost;
import com.tripoin.rest.bgp.impl.BGPLogin;
import com.tripoin.rest.dto.app.DTOParcelUserLogin;
import com.tripoin.rest.dto.request.login.DTOParcelRequestLogin;
import com.tripoin.rest.dto.response.DTOBaseResponse;
import com.tripoin.rest.post.IGenericPost;
import com.tripoin.rest.post.IPostLogin;

import org.parceler.Parcels;

import com.tripoin.rest.util.TRIPOINHUDProgressDialog;
import com.tripoin.util.network.DaggerNetworkComponent;
import com.tripoin.util.network.NetworkComponent;
import com.tripoin.util.network.NetworkConnectivity;
import com.tripoin.util.network.NetworkModule;
import retrofit.RetrofitError;

/**
 * Created on 9/30/2015 : 5:42 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
public abstract class ALoginHandler<RESPONSE> implements ILoginHandler, IPostLogin<RESPONSE> {

    private String password;
    private ModelUser modelUser;
    private IGenericPost iGenericPost;
    private GeneralValidation generalValidation;
    private String tempUserName = "081221356663";

    DAOComponent daoComponent;
    DAOUser daoUser;
    NetworkComponent networkComponent;
    NetworkConnectivity networkConnectivity;

    protected ALoginHandler() {
        generalValidation = new GeneralValidation();
        daoComponent = DaggerDAOComponent.builder().dAOModule(new DAOModule(getContext())).build();
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule(getContext())).build();
        daoUser = daoComponent.provideDAOUser();
        networkConnectivity = networkComponent.provideNetworkConnectivity();
    }

    @Override
    public void doLogin() {
        if (!generalValidation.isEmptyEditText(getTxtPassword())) {
            password = getTxtPassword().getText().toString().trim();
            if (networkConnectivity.isConnected()) {
                //daoUser = new DAOUser(getContext());
                try {
                    modelUser = (ModelUser) daoUser.getAllData().get(0);
                    daoUser.updateEntity(initBaseUser(modelUser));
                } catch (Exception e) {
                    modelUser = new ModelUser();
                    daoUser.insertEntity(initBaseUser(modelUser));
                }
                final TRIPOINHUDProgressDialog ticketingHUDProgressDialog = TRIPOINHUDProgressDialog.show(
                        ALoginHandler.this.getContext(),
                        ALoginHandler.this.getContext().getResources().getString(R.string.login_pd_message),
                        false,
                        null);
                iGenericPost = new DialogGenericPost(ticketingHUDProgressDialog);
                new BGPLogin(this) {
                    @Override
                    public Context getContext() {
                        return ALoginHandler.this.getContext();
                    }

                    @Override
                    public Dialog getProgressDialog() {
                        return ticketingHUDProgressDialog;
                    }

                    @Override
                    public DTOParcelRequestLogin getDTORequestLogin() {
                        DTOParcelRequestLogin dtoRequestLogin = new DTOParcelRequestLogin();
                        dtoRequestLogin.setViewType("MOBILE");
                        return dtoRequestLogin;
                    }
                }.execute();
            } else {
                Toast.makeText(getContext(), R.string.internet_unavailable, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), R.string.password_empty, Toast.LENGTH_SHORT).show();
        }
        //byPassLogin();
    }


    private ModelUser initBaseUser(ModelUser modelUser){
        modelUser.setUserName(tempUserName);
        modelUser.setChipperAuth(tempUserName, getTxtPassword().getText().toString().trim());
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

    @Override
    public void onPostSuccess(RESPONSE p_Response) {
        if(p_Response != null){
            iGenericPost.onPostSuccess(p_Response);
            DTOBaseResponse dtoResponseLogin = (DTOBaseResponse) p_Response;
            Log.i(LogTag.TRIPOIN_INFO, dtoResponseLogin.toString());
            if(dtoResponseLogin.getResponseCode() == BinaryValue.ZERO){
                modelUser = (ModelUser) daoUser.getAllData().get(0);
                modelUser.setLoginStatus(BinaryValue.ONE);
                modelUser.setUserName(tempUserName);
                modelUser.setUserCode("ADMIN");
                daoUser.updateEntity(modelUser);

                DTOParcelUserLogin DTOUserLogin = new DTOParcelUserLogin();
                DTOUserLogin.setUserName(modelUser.getUserName());
                DTOUserLogin.setUserCode(modelUser.getUserCode());

                NavigatorActivity navigatorActivity = new NavigatorActivity();
                navigatorActivity.setParameter(getContext());
                navigatorActivity.gotoNextActivity(
                        getSuccessClass(),
                        TransferKeys.USER_LOGIN, Parcels.wrap(DTOUserLogin)
                );
            }
        }
    }

    @Override
    public void onPostFailure(RetrofitError p_RetrofitError) {
        iGenericPost.onPostFailure(p_RetrofitError);
        Log.e(LogTag.TRIPOIN_ERROR, p_RetrofitError.getMessage());
        Toast.makeText(getContext(), p_RetrofitError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void byPassLogin(){
        DTOParcelUserLogin DTOUserLogin = new DTOParcelUserLogin();
        DTOUserLogin.setUserName(Login.SAMPLE_USER);
        DTOUserLogin.setUserCode(Login.SAMPLE_ROLE);

        //daoUser = new DAOUser(getContext());
        modelUser = (ModelUser) daoUser.getAllData().get(0);
        modelUser.setLoginStatus(GeneralConstant.BinaryValue.ONE);
        modelUser.setUserName(DTOUserLogin.getUserName());
        modelUser.setUserCode(DTOUserLogin.getUserCode());
        daoUser.updateEntity(modelUser);

        NavigatorActivity navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(getContext());
        navigatorActivity.gotoNextActivity(
                getSuccessClass(),
                ApplicationConstant.TransferKeys.USER_LOGIN, Parcels.wrap(DTOUserLogin)
        );
    }
}
