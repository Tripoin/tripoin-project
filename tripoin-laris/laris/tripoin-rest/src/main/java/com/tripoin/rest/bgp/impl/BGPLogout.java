package com.tripoin.rest.bgp.impl;

import com.tripoin.rest.bgp.base.ABGP;
import com.tripoin.rest.callback.GenericCallBack;
import com.tripoin.rest.endpoints.EPLogout;
import com.tripoin.rest.post.IGenericPost;

import retrofit.Callback;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPLogout extends ABGP {

    public BGPLogout(IGenericPost p_IGenericPost) {
        super(p_IGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] p_MultipleParams) {
        EPLogout epLogout = (EPLogout) restGenerator.createService(getEPClass());
        epLogout.logout(getCallBack());
        return null;
    }

    @Override
    public Callback getCallBack() {
        return new GenericCallBack(iGenericPost);
    }

    @Override
    public Class getEPClass() {
        return EPLogout.class;
    }

}
