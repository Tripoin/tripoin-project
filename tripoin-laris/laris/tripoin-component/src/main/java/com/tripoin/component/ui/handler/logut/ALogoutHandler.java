package com.tripoin.component.ui.handler.logut;

import android.util.Log;
import android.widget.Toast;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.R;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.rest.dto.response.logout.DTOResponseLogout;
import com.tripoin.rest.post.IPostLogout;
import com.tripoin.util.network.NetworkConnectivity;

import retrofit.RetrofitError;

/**
 * Created on 10/8/2015 : 10:58 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
public abstract class ALogoutHandler<RESPONSE> implements ILogoutHandler, IPostLogout<RESPONSE> {

    private DAOUser daoUser;
    private ModelUser modelUser;
    private NavigatorActivity navigatorActivity;

    public ALogoutHandler() {
        daoUser = new DAOUser(getContext());
        navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(getContext());
    }

    @Override
    public void detectLoginStatus() {
        try{
            modelUser = (ModelUser) daoUser.getAllData().get(0);
            if(modelUser != null){
                if(modelUser.getLoginStatus() != GeneralConstant.BinaryValue.ONE){
                    gotoLoginScreen();
                }
            }else{
                gotoLoginScreen();
            }
        }catch (Exception e){
            initNewUser();
            gotoLoginScreen();
        }
    }

    private void gotoLoginScreen(){
        navigatorActivity.gotoNextActivity(
                getLoginClass(),
                GeneralConstant.Punctuation.EMPTY,
                GeneralConstant.Punctuation.EMPTY
        );
    }

    @Override
    public void doLogout() {
        NetworkConnectivity networkConnectivity = new NetworkConnectivity(getContext());
        if(networkConnectivity.isConnected()) {
            /*new BGPLogout(this) {
                @Override
                public Context getContext() {
                    return ALogoutHandler.this.getContext();
                }

                @Override
                public Dialog getProgressDialog() {
                    return TRIPOINHUDProgressDialog.show(
                            ALogoutHandler.this.getContext(),
                            ALogoutHandler.this.getContext().getResources().getString(R.string.logout_pd_message),
                            false,
                            null);
                }
            }.execute();*/
            postProcessing();
        }else{
            Toast.makeText(getContext(), R.string.internet_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostSuccess(RESPONSE p_Response) {
        if(p_Response != null){
            DTOResponseLogout dtoResponseLogout = (DTOResponseLogout) p_Response;
            Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, dtoResponseLogout.toString());
            if(dtoResponseLogout.getResponseCode() == GeneralConstant.BinaryValue.ZERO){
                postProcessing();
            }
        }
    }

    private void postProcessing(){
        modelUser = (ModelUser) daoUser.getAllData().get(0);
        if(modelUser != null){
            modelUser.setLoginStatus(GeneralConstant.BinaryValue.ZERO);
            daoUser.updateEntity(modelUser);
        }else{
            initNewUser();
        }
        navigatorActivity.exitApplication();
    }

    private void initNewUser(){
        modelUser = new ModelUser();
        modelUser.setUserName(ApplicationConstant.Database.INIT_USER);
        modelUser.setUserCode(GeneralConstant.Punctuation.EMPTY);
        modelUser.setIsActive(GeneralConstant.BinaryValue.ONE);
        modelUser.setLoginStatus(GeneralConstant.BinaryValue.ZERO);
        daoUser.insertEntity(modelUser);
    }

    @Override
    public void onPostFailure(RetrofitError p_RetrofitError) {
        Log.e(ApplicationConstant.LogTag.TRIPOIN_ERROR, p_RetrofitError.getMessage());
        Toast.makeText(
                getContext(),
                getContext().getResources().getString(R.string.logout_failed),
                Toast.LENGTH_SHORT
        ).show();
    }
}
