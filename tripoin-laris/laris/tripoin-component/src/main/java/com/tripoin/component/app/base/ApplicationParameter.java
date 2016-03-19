package com.tripoin.component.app.base;

import com.tripoin.dao.DAOHelper;
import com.tripoin.dao.impl.DAODynamicConfiguration;
import com.tripoin.dao.impl.DAOUser;

/**
 * Created on 2/8/2016 : 1:51 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ApplicationParameter {

    private DAOHelper daoHelper;
    private DAOUser daoUser;
    private DAODynamicConfiguration daoDynamicConfiguration;

    public DAOHelper getDaoHelper() {
        return daoHelper;
    }

    public void setDaoHelper(DAOHelper daoHelper) {
        this.daoHelper = daoHelper;
    }

    public DAOUser getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    public DAODynamicConfiguration getDaoDynamicConfiguration() {
        return daoDynamicConfiguration;
    }

    public void setDaoDynamicConfiguration(DAODynamicConfiguration daoDynamicConfiguration) {
        this.daoDynamicConfiguration = daoDynamicConfiguration;
    }
}
