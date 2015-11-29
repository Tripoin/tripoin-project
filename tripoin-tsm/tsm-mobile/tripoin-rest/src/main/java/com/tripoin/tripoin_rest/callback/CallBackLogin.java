package com.tripoin.tripoin_rest.callback;

import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.dto.response.login.DTOResponseLogin;
import com.tripoin.tripoin_rest.post.IGenericPost;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created on 10/2/2015 : 10:28 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CallBackLogin implements Callback<DTOResponseLogin>{

    private IGenericPost iGenericPost;

    public CallBackLogin(IGenericPost iGenericPost) {
        this.iGenericPost = iGenericPost;
    }

    @Override
    public void success(DTOResponseLogin dtoResponseLogin, Response response) {
        if(response.getStatus() == ApplicationConstant.Rest.Status.SUCCESS){
            DTOResponseLogin result = dtoResponseLogin;
            Log.i(ApplicationConstant.Log.TRIPOIN_INFO, dtoResponseLogin.toString());
            iGenericPost.onPostSuccess(result);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        iGenericPost.onPostFailure(error);
    }

}
