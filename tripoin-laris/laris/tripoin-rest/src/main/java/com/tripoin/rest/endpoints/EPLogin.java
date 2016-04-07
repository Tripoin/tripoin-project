package com.tripoin.rest.endpoints;

import com.tripoin.common.constant.ApplicationConstant.Rest.EndPoints;
import com.tripoin.rest.dto.request.login.DTOParcelRequestLogin;
import com.tripoin.rest.dto.response.DTOBaseResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * <p>
 *     Login End Point
 * </p>
 * Created on 6/1/2015 : 11:44 AM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EPLogin {

    @POST(EndPoints.LOGIN)
    void login(
            @Body DTOParcelRequestLogin p_DtoRequestLogin,
            Callback<DTOBaseResponse> p_DtoBaseResponseCallback
    );
}
