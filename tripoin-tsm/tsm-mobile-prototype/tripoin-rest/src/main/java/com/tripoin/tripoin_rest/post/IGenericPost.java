package com.tripoin.tripoin_rest.post;

import retrofit.RetrofitError;

/**
 * Created on 10/1/2015 : 5:01 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
public interface IGenericPost<RESPONSE> {

    void onPostSuccess(RESPONSE response);

    void onPostFailure(RetrofitError retrofitError);
}
