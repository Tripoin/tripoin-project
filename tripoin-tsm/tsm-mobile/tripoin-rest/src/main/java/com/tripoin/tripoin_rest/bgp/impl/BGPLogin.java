package com.tripoin.tripoin_rest.bgp.impl;

import com.tripoin.tripoin_rest.bgp.base.ABGP;
import com.tripoin.tripoin_rest.callback.GenericCallBack;
import com.tripoin.tripoin_rest.endpoints.EPLogin;
import com.tripoin.tripoin_rest.post.IGenericPost;

import retrofit.Callback;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPLogin extends ABGP{

    public BGPLogin(IGenericPost iGenericPost) {
        super(iGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        EPLogin epLogin = (EPLogin) restGenerator.createService(getEPClass());
        epLogin.login(getCallBack());
        return null;
    }

    @Override
    public Callback getCallBack() {
        return new GenericCallBack(iGenericPost);
    }

    @Override
    public Class getEPClass() {
        return EPLogin.class;
    }
}
