package com.tripoin.xwp.rest.service.impl;

import com.tripoin.xwp.data.dto.common.DTOGeraiMikrotik;
import com.tripoin.xwp.data.entity.EntityGeraiMikrotik;
import com.tripoin.xwp.rest.constant.ApplicationConstant;
import com.tripoin.xwp.rest.constant.ApplicationConstant.SPRING_COMPONENT.SERVICE;
import com.tripoin.xwp.rest.mikrotik.MikrotikConnection;
import com.tripoin.xwp.rest.repository.RepoGeraiMikrotik;
import com.tripoin.xwp.rest.service.IServiceMikrotik;
import me.legrange.mikrotik.MikrotikApiException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ladies Man on 2/27/2016.
 */
@Service(SERVICE.SERVICE_MIKROTIK)
public class ServiceMikrotikImpl implements IServiceMikrotik{

    @Autowired
    RepoGeraiMikrotik repoGeraiMikrotik;

    Logger logger = LoggerFactory.getLogger(ServiceMikrotikImpl.class);


    @Override
    public DTOGeraiMikrotik selectGeraiMikrotikByCode(String p_Code) {
        EntityGeraiMikrotik entityGeraiMikrotik = repoGeraiMikrotik.selectGeraiMikrotikByCode(p_Code);
        if(entityGeraiMikrotik != null){
            DTOGeraiMikrotik dtoGeraiMikrotik = new DTOGeraiMikrotik();
            dtoGeraiMikrotik.setUserName(entityGeraiMikrotik.getUserName());
            dtoGeraiMikrotik.setPassword(entityGeraiMikrotik.getPassword());
            dtoGeraiMikrotik.setIpAddress(entityGeraiMikrotik.getIpAddress());
            dtoGeraiMikrotik.setDescription(entityGeraiMikrotik.getDescription());
            return dtoGeraiMikrotik;
        }else{
            return null;
        }
    }

    @Override
    public List<Map<String, String>> getAllInterfaces(String p_MikrotikName) {
        DTOGeraiMikrotik dtoGeraiMikrotik = selectGeraiMikrotikByCode(p_MikrotikName);
        List<Map<String, String>> result = new ArrayList<>();
        if(dtoGeraiMikrotik != null){
            logger.info(ApplicationConstant.LOG.INFO, dtoGeraiMikrotik.toString());
            MikrotikConnection mikrotikConnection = new MikrotikConnection();
            List<Map<String, String>> rs = null;

            try {
                rs = mikrotikConnection.getInstance(dtoGeraiMikrotik).execute("/interface/print");
            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }

            for (Map<String,String> r : rs) {
                result.add(r);
            }
        }
        return result;
    }


}
