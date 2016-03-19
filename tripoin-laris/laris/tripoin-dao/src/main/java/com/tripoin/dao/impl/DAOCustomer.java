package com.tripoin.dao.impl;

import android.content.Context;

import javax.inject.Inject;

import com.tripoin.dao.base.ABaseGenericDAO;
import com.tripoin.model.ModelCustomer;

/**
 * Created on 10/11/2015 : 4:50 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DAOCustomer extends ABaseGenericDAO {

    @Inject
    public DAOCustomer(Context p_Context) {
        super(p_Context);
    }

    @Override
    public Class getModelClass() {
        return ModelCustomer.class;
    }
}
