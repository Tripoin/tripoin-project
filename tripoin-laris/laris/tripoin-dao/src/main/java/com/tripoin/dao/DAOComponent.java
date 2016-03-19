package com.tripoin.dao;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.tripoin.dao.impl.DAOCustomer;
import com.tripoin.dao.impl.DAODynamicConfiguration;
import com.tripoin.dao.impl.DAOUser;

/**
 * <p>
 *     Component for DAO Dependency Injection
 * </p>
 * Created on 2/8/2016 : 10:43 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Singleton
@Component(modules = DAOModule.class)
public interface DAOComponent {

    DAOHelper provideDAOHelper();

    DAOUser provideDAOUser();

    DAODynamicConfiguration provideDAODynamicConfiguration();

    DAOCustomer provideDAOCustomer();

    void inject(Context context);
}
