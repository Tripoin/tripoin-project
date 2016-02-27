package com.tripoin.xwp.rest.mikrotik;

import com.tripoin.xwp.data.dto.common.DTOGeraiMikrotik;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

/**
 * Created by Ladies Man on 2/27/2016.
 */
public class MikrotikConnection {

    private ApiConnection apiConnection;
    public ApiConnection getInstance(DTOGeraiMikrotik p_DTOGeraiMikrotik) {
        try {
            apiConnection = ApiConnection.connect(p_DTOGeraiMikrotik.getIpAddress());
            String password = null;
            if(p_DTOGeraiMikrotik.getPassword() == null){
                password = "";
            }
            apiConnection.login(p_DTOGeraiMikrotik.getUserName(), password); // log in to router
        } catch (MikrotikApiException e) {
            e.printStackTrace();
        }
        return apiConnection;
    }

    public ApiConnection getApiConnection(){
        if(apiConnection != null){
            return apiConnection;
        }else{
            return null;
        }
    }

}
