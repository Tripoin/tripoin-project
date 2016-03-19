package com.tripoin.rest.engine;


import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import com.tripoin.common.component.ITRIPOINComponent;

import com.tripoin.dao.DAOComponent;
import com.tripoin.dao.DAOModule;
import com.tripoin.dao.DaggerDAOComponent;
import com.tripoin.dao.impl.DAODynamicConfiguration;
import com.tripoin.model.ModelDynamicConfiguration;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @param <GENERATOR>
 * Created on 5/24/2015 : 12:21 AM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class RestGenerator<GENERATOR> implements ITRIPOINComponent<RestParameter> {

    public RestAdapter.Builder builder = new RestAdapter.Builder();
    private RestParameter restParameter;

    private DAOComponent daoComponent;

    public GENERATOR createService(Class<GENERATOR> p_ServiceClass) {
        return createService(
                p_ServiceClass,
                getParameter().getChipperAuth(),
                getParameter().getContext()
        );
    }

    public GENERATOR createService(Class<GENERATOR> p_ServiceClass,  String p_ChipperAuth, Context p_Context) {
        daoComponent = DaggerDAOComponent.builder().dAOModule(new DAOModule(p_Context)).build();
        DAODynamicConfiguration daoDynamicConfiguration = daoComponent.provideDAODynamicConfiguration();
        ModelDynamicConfiguration modelDynamicConfiguration = (ModelDynamicConfiguration)
                daoDynamicConfiguration.getAllData().get(0);

        builder.setLogLevel(RestAdapter.LogLevel.FULL)
               .setEndpoint(modelDynamicConfiguration.getBasicUrl())
               .setClient(new OkClient(new OkHttpClient()));

        if (p_ChipperAuth != null) {
            builder.setRequestInterceptor(new CustomRequestInterceptor(p_ChipperAuth));
        }

        RestAdapter adapter = builder.build();
        return adapter.create(p_ServiceClass);
    }

    @Override
    public void setParameter(RestParameter p_Param) {
        this.restParameter = p_Param;
    }

    @Override
    public RestParameter getParameter() {
        return restParameter;
    }
}