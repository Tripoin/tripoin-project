package com.tripoin.rest.endpoints;

import java.util.List;

import com.tripoin.common.constant.ApplicationConstant.Rest.EndPoints;
import com.tripoin.rest.dto.request.location.DTORequestLocation;
import com.tripoin.rest.dto.response.location.DTOResponseLocation;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created on 6/1/2015 : 11:44 AM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EPLocation {

    @POST(EndPoints.LOCATION)
    void getLocation(
            @Body DTORequestLocation dtoRequestLocation,
            Callback<List<DTOResponseLocation>> dtoResponseLocation
    );
}
