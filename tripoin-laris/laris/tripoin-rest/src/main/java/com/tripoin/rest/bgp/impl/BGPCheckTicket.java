package com.tripoin.rest.bgp.impl;

import com.tripoin.rest.bgp.base.ABGP;
import com.tripoin.rest.callback.GenericCallBack;
import com.tripoin.rest.dto.request.checkticket.DTORequestCheckTicket;
import com.tripoin.rest.endpoints.EPCheckTicket;
import com.tripoin.rest.post.IGenericPost;
import retrofit.Callback;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPCheckTicket extends ABGP{

    public BGPCheckTicket(IGenericPost iGenericPost) {
        super(iGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] p_MultipleParams) {
        EPCheckTicket epCheckTicket = (EPCheckTicket) restGenerator.createService(getEPClass());
        epCheckTicket.checkTicket(
                getDTORequestCheckTicket().getRqid(),
                getDTORequestCheckTicket().getApp(),
                getDTORequestCheckTicket().getAction(),
                getDTORequestCheckTicket().getBookingCode(),
                getCallBack()
        );
        return null;
    }

    public abstract DTORequestCheckTicket getDTORequestCheckTicket();

    @Override
    public Callback getCallBack() {
        return new GenericCallBack(iGenericPost);
    }

    @Override
    public Class getEPClass() {
        return EPCheckTicket.class;
    }
}
