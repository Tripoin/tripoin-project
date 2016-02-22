package com.tripoin.tripoin_component.app;

import android.content.Context;

/**
 * Created on 9/28/2015 : 7:54 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface ITRIPOINApplication {


    Context getContext();

    void initBaseApplicationComponents();

    void initDefaultUser();
    
}
