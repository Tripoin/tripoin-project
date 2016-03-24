package com.tripoin.component.ui.activity;

import com.tripoin.dao.DAOComponent;
import com.tripoin.util.network.NetworkComponent;

/**
 * Created on 3/23/2016 : 1:06 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IComponentInjector {

    void injectNetworkComponent(NetworkComponent p_NetworkComponent);

    void injectDAOComponent(DAOComponent p_DAOComponet);
}
