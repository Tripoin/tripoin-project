package com.tripoin.tripoin_rest.bgp.base;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_dao.impl.DAOUser;
import com.tripoin.tripoin_model.ModelUser;
import com.tripoin.tripoin_rest.bgp.IBGP;
import com.tripoin.tripoin_rest.engine.RestGenerator;
import com.tripoin.tripoin_rest.engine.RestParameter;
import com.tripoin.tripoin_rest.post.IGenericPost;

/**
 * Created on 10/1/2015 : 4:59 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABGP extends AsyncTask implements IBGP{

    protected RestGenerator restGenerator;
    protected IGenericPost iGenericPost;
    protected RestParameter restParameter;
    protected Dialog dialog;

    public ABGP(IGenericPost iGenericPost) {
        this. restGenerator = new RestGenerator();
        this.iGenericPost = iGenericPost;

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

    private ModelUser initBaseUser(ModelUser modelUser){
        modelUser.setIsActive(GeneralConstant.BinaryValue.ZERO);
        modelUser.setLoginStatus(GeneralConstant.BinaryValue.ZERO);
        modelUser.setUserName(ApplicationConstant.Database.INIT_USER);
        modelUser.setChipperAuth(GeneralConstant.Punctuation.EMPTY, GeneralConstant.Punctuation.EMPTY);
        return modelUser;
    }

    private RestParameter initBasicRestParameter(ModelUser modelUser) {
        RestParameter result = new RestParameter();
        result.setContext(getContext());
        if(modelUser.getChipperAuth() == null){
            result.setChipperAuth(GeneralConstant.Punctuation.EMPTY);
        }else{
            result.setChipperAuth(modelUser.getChipperAuth());
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
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Post Execute BGP");
    }



}
