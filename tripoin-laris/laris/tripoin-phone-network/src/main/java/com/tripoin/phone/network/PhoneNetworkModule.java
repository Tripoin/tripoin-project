package com.tripoin.phone.network;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 2/23/2016 : 8:00 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Module
public class PhoneNetworkModule {

    @Singleton
    @Provides
    BatchPhoneNetwork providePhoneNetwork(Context p_Context){
        BatchPhoneNetwork batchPhoneNetwork = new BatchPhoneNetwork();
        return batchPhoneNetwork;
    }
}
