package com.tripoin.tripoin_component.app.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_component.R;
import com.tripoin.tripoin_component.app.ITRIPOINApplication;
import com.tripoin.tripoin_dao.DAOHelper;
import com.tripoin.tripoin_dao.impl.DAODynamicConfiguration;
import com.tripoin.tripoin_dao.impl.DAOUser;
import com.tripoin.tripoin_model.ModelDynamicConfiguration;
import com.tripoin.tripoin_model.ModelUser;

/**
 * Created on 9/28/2015 : 7:57 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ATRIPOINApplication  extends MultiDexApplication implements ITRIPOINApplication {

    private DAOHelper daoHelper;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, getResources().getString(R.string.base_init));
        initBaseApplicationComponents();
        initDefaultUser();
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, getResources().getString(R.string.base_start));
    }

    @Override
    public void initBaseApplicationComponents() {
        daoHelper = new DAOHelper(getContext());
        daoHelper.getWritableDatabase();

        DAODynamicConfiguration daoDynamicConfiguration = new DAODynamicConfiguration(this);
        ModelDynamicConfiguration modelDynamicConfiguration;
        try{
            modelDynamicConfiguration = (ModelDynamicConfiguration) daoDynamicConfiguration.getAllData().get(0);
            if(modelDynamicConfiguration.getHost() != null){
                daoDynamicConfiguration.updateEntity(initBaseConfig(modelDynamicConfiguration));
            }
        }catch (Exception e){
            Log.e(ApplicationConstant.Log.TRIPOIN_ERROR, getResources().getString(R.string.dynamic_config_not_found));
            modelDynamicConfiguration = new ModelDynamicConfiguration();
            daoDynamicConfiguration.insertEntity(initBaseConfig(modelDynamicConfiguration));
        }
    }

    private ModelDynamicConfiguration initBaseConfig(ModelDynamicConfiguration modelDynamicConfiguration){
        modelDynamicConfiguration.setHost(ApplicationConstant.Rest.INIT_HOST);
        modelDynamicConfiguration.setPort(ApplicationConstant.Rest.INIT_PORT);
        return modelDynamicConfiguration;
    }

    @Override
    public void initDefaultUser() {
        DAOUser daoUser = new DAOUser(getContext());
        ModelUser modelUser;
        try{
            modelUser = (ModelUser) daoUser.getAllData().get(0);
            if(modelUser.getUserName() == null){
                daoUser.updateEntity(initBaseUser(modelUser));
            }
        }catch (Exception e){
            Log.e(ApplicationConstant.Log.TRIPOIN_ERROR, getResources().getString(R.string.dynamic_config_not_found));
            modelUser = new ModelUser();
            daoUser.insertEntity(initBaseUser(modelUser));
        }
    }

    private ModelUser initBaseUser(ModelUser modelUser){
        modelUser.setIsActive(GeneralConstant.BinaryValue.ZERO);
        modelUser.setLoginStatus(GeneralConstant.BinaryValue.ZERO);
        modelUser.setUserName(ApplicationConstant.Database.INIT_USER);
        return modelUser;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (daoHelper != null) {
            daoHelper.close();
        }
    }
}
