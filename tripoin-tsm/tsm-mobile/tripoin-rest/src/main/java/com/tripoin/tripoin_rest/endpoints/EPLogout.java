package com.tripoin.tripoin_rest.endpoints;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.dto.response.logout.DTOResponseLogout;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created on 6/1/2015 : 11:44 AM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EPLogout {

    @GET(ApplicationConstant.Rest.EndPoints.LOGOUT)
    void logout(Callback<DTOResponseLogout> dtoResponseLogoutCallback);
}
