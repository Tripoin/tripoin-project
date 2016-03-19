package com.tripoin.component.ui.handler.login;


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
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.dao.DAOComponent;
import com.tripoin.dao.DAOModule;
import com.tripoin.dao.DaggerDAOComponent;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.rest.dto.app.DTOUserLogin;
import com.tripoin.rest.dto.response.DTOBaseResponse;
import com.tripoin.rest.post.IGenericPost;
import com.tripoin.rest.post.IPostLogin;

import org.parceler.Parcels;

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

    private String userName;
    private String password;
    private ModelUser modelUser;
    private IGenericPost iGenericPost;
    private GeneralValidation generalValidation;

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
        /*if(!generalValidation.isEmptyEditText(getTxtUserName())){
            userName = getTxtUserName().getText().toString().trim();
            if(!generalValidation.isEmptyEditText(getTxtPassword())){
                password = getTxtPassword().getText().toString().trim();
                if(networkConnectivity.isConnected()){
                    //daoUser = new DAOUser(getContext());
                    try{
                        modelUser = (ModelUser) daoUser.getAllData().get(0);
                        daoUser.updateEntity(initBaseUser(modelUser));
                    }catch (Exception e){
                        modelUser = new ModelUser();
                        daoUser.insertEntity(initBaseUser(modelUser));
                    }
                    final TicketingHUDProgressDialog ticketingHUDProgressDialog = TicketingHUDProgressDialog.show(
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
                        public DTORequestLogin getDTORequestLogin() {
                            DTORequestLogin dtoRequestLogin = new DTORequestLogin();
                            dtoRequestLogin.setUserName(userName);
                            dtoRequestLogin.setPassword(password);
                            return dtoRequestLogin;
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
        }*/
        byPassLogin();
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
    public void onPostSuccess(RESPONSE p_Response) {/*
        if(response != null){
            iGenericPost.onPostSuccess(response);
            DTOResponseLogin dtoResponseLogin = (DTOResponseLogin) response;
            Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, dtoResponseLogin.toString());
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
        }*/

        if(p_Response != null){
            iGenericPost.onPostSuccess(p_Response);
            DTOBaseResponse dtoResponseLogin = (DTOBaseResponse) p_Response;
            Log.i(LogTag.TRIPOIN_INFO, dtoResponseLogin.toString());
            if(dtoResponseLogin.getResponseCode() == BinaryValue.ZERO){
                modelUser = (ModelUser) daoUser.getAllData().get(0);
                modelUser.setLoginStatus(BinaryValue.ONE);
                modelUser.setUserName(userName);
                modelUser.setUserCode("ADMIN");
                daoUser.updateEntity(modelUser);

                DTOUserLogin DTOUserLogin = new DTOUserLogin();
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
        DTOUserLogin DTOUserLogin = new DTOUserLogin();
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
