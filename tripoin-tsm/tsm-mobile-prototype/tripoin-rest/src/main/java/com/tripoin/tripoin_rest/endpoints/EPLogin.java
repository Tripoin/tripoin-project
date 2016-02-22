package com.tripoin.tripoin_rest.endpoints;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.dto.response.DTOBaseResponse;
import com.tripoin.tripoin_rest.dto.response.login.DTOResponseLogin;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created on 6/1/2015 : 11:44 AM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EPLogin {

    @GET(ApplicationConstant.Rest.EndPoints.LOGIN)
    void login(Callback<DTOResponseLogin> dtoResponseLoginCallback);
}
