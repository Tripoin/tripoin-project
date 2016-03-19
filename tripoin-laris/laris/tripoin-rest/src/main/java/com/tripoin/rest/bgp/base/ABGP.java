package com.tripoin.rest.bgp.base;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.rest.R;
import com.tripoin.rest.bgp.IBGP;
import com.tripoin.rest.engine.RestGenerator;
import com.tripoin.rest.engine.RestParameter;
import com.tripoin.rest.post.IGenericPost;

/**
 * Created on 10/1/2015 : 4:59 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABGP extends AsyncTask implements IBGP {

    protected RestGenerator restGenerator;
    protected IGenericPost iGenericPost;
    protected RestParameter restParameter;
    protected Dialog dialog;

    public ABGP(IGenericPost p_IGenericPost) {
        this. restGenerator = new RestGenerator();
        this.iGenericPost = p_IGenericPost;

        initRestParameter();
        restGenerator.setParameter(restParameter);
    }

    private void initRestParameter(){
        DAOUser daoUser = new DAOUser(getContext());
        ModelUser modelUser;
        try{
            modelUser = (ModelUser)daoUser.getAllData().get(0);
            if(modelUser.getChipperAuth() == null){
                daoUser.updateEntity(initBaseUser(modelUser));
            }
            this.restParameter = initBasicRestParameter(modelUser);
        } catch (Exception e){
            modelUser = new ModelUser();
            daoUser.insertEntity(initBaseUser(modelUser));
        }
    }

    private ModelUser initBaseUser(ModelUser p_ModelUser){
        p_ModelUser.setIsActive(GeneralConstant.BinaryValue.ZERO);
        p_ModelUser.setLoginStatus(GeneralConstant.BinaryValue.ZERO);
        p_ModelUser.setUserName(ApplicationConstant.Database.INIT_USER);
        p_ModelUser.setChipperAuth(GeneralConstant.Punctuation.EMPTY, GeneralConstant.Punctuation.EMPTY);
        return p_ModelUser;
    }

    private RestParameter initBasicRestParameter(ModelUser p_ModelUser) {
        RestParameter result = new RestParameter();
        result.setContext(getContext());
        if(p_ModelUser.getChipperAuth() == null){
            result.setChipperAuth(GeneralConstant.Punctuation.EMPTY);
        }else{
            result.setChipperAuth(p_ModelUser.getChipperAuth());
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(dialog != null){
            dialog.show();
        }else if(dialog == null){
            if(getProgressDialog() != null){
                dialog = getProgressDialog();
                dialog.dismiss();
                dialog.show();
            }
        }
    }

    @Override
    protected void onPostExecute(Object p_Object) {
        super.onPostExecute(p_Object);
        Log.i(
                ApplicationConstant.LogTag.TRIPOIN_INFO,
                getContext().getResources().getString(R.string.post_execute_bgp).concat(getEPClass().getSimpleName())
        );
        if(dialog != null){
            dialog.dismiss();
        }
    }
}
