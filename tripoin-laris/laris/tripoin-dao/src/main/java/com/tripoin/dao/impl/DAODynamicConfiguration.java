package com.tripoin.dao.impl;

import android.content.Context;

import javax.inject.Inject;

import com.tripoin.dao.base.ABaseGenericDAO;
import com.tripoin.model.ModelDynamicConfiguration;

/**
 * Created on 9/25/2015 : 9:07 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DAODynamicConfiguration extends ABaseGenericDAO {

    @Inject
    public DAODynamicConfiguration(Context p_Context) {
        super(p_Context);
    }

    @Override
    public Class getModelClass() {
        return ModelDynamicConfiguration.class;
    }
}
