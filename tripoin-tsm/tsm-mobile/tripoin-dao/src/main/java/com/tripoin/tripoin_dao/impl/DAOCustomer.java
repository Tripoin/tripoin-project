package com.tripoin.tripoin_dao.impl;

import android.content.Context;

import com.tripoin.tripoin_dao.base.ABaseGenericDAO;
import com.tripoin.tripoin_model.ModelCustomer;

/**
 * Created on 10/11/2015 : 4:50 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DAOCustomer extends ABaseGenericDAO {

    public DAOCustomer(Context ctx) {
        super(ctx);
    }

    @Override
    public Class getModelClass() {
        return ModelCustomer.class;
    }
}
