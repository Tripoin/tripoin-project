package com.tripoin.dao.impl;

import android.content.Context;

import javax.inject.Inject;

import com.tripoin.dao.base.ABaseGenericDAO;
import com.tripoin.model.ModelUser;

/**
 * Created on 5/27/2015 : 4:24 PM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 *
 */
public class DAOUser extends ABaseGenericDAO {

    @Inject
    public DAOUser(Context p_Context) {
        super(p_Context);
    }

    @Override
    public Class getModelClass() {
        return ModelUser.class;
    }
}
