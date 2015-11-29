package com.tripoin.tripoin_rest.bgp.impl;

import com.tripoin.tripoin_rest.bgp.base.ABGP;
import com.tripoin.tripoin_rest.callback.GenericCallBack;
import com.tripoin.tripoin_rest.endpoints.EPLogout;
import com.tripoin.tripoin_rest.post.IGenericPost;

import retrofit.Callback;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPLogout extends ABGP {

    public BGPLogout(IGenericPost iGenericPost) {
        super(iGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] params) {
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
