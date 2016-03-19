package com.tripoin.rest.bgp.impl;

import com.tripoin.rest.bgp.base.ABGP;
import com.tripoin.rest.callback.GenericCallBack;
import com.tripoin.rest.dto.request.getticket.DTORequestGetTicket;
import com.tripoin.rest.endpoints.EPGetTicket;
import com.tripoin.rest.post.IGenericPost;
import retrofit.Callback;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPGetTicket extends ABGP{

    public BGPGetTicket(IGenericPost iGenericPost) {
        super(iGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] p_MultipleParams) {
        EPGetTicket epGetTicket = (EPGetTicket) restGenerator.createService(getEPClass());
        epGetTicket.checkTicket(
                getDTORequestGetTicket().getRqid(),
                getDTORequestGetTicket().getApp(),
                getDTORequestGetTicket().getAction(),
                getDTORequestGetTicket().getBookingCode(),
                getCallBack()
        );
        return null;
    }

    public abstract DTORequestGetTicket getDTORequestGetTicket();

    @Override
    public Callback getCallBack() {
        return new GenericCallBack(iGenericPost);
    }

    @Override
    public Class getEPClass() {
        return EPGetTicket.class;
    }
}
