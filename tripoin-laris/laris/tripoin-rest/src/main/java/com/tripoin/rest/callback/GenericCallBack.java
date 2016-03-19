package com.tripoin.rest.callback;

import com.tripoin.common.constant.ApplicationConstant.Rest.Status;
import com.tripoin.rest.post.IGenericPost;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created on 10/2/2015 : 10:28 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESULT>
 */
public class GenericCallBack<RESULT> implements Callback<RESULT>{

    private IGenericPost iGenericPost;

    public GenericCallBack(IGenericPost p_IGenericPost) {
        this.iGenericPost = p_IGenericPost;
    }

    @Override
    public void success(RESULT p_Result, Response p_Response) {
        if(p_Response.getStatus() == Status.SUCCESS){
            iGenericPost.onPostSuccess(p_Result);
        }
    }

    @Override
    public void failure(RetrofitError p_RetrofitError) {
        iGenericPost.onPostFailure(p_RetrofitError);
    }

}
