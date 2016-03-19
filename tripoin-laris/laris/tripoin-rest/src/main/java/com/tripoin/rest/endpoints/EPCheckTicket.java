package com.tripoin.rest.endpoints;

import com.tripoin.common.constant.ApplicationConstant.Rest.DTO.Response.BaseTicketing;
import com.tripoin.common.constant.ApplicationConstant.Rest.EndPoints;
import com.tripoin.rest.dto.response.checkticket.DTOResponseCheckTicket;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * <p>
 *     Check Ticket End Point
 * </p>
 * Created on 6/1/2015 : 11:44 AM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface EPCheckTicket {

    @GET(EndPoints.CHECK_TICKET)
    void checkTicket(
            @Path(BaseTicketing.RQID) String rqid,
            @Path(BaseTicketing.APP) String app,
            @Path(BaseTicketing.ACTION) String action,
            @Path(BaseTicketing.BOOK_CODE) String bookCode,
            Callback<DTOResponseCheckTicket> dtoCheckTicketCallback
    );
}
