package com.tripoin.xwp.rest.mikrotik.command.impl;

import com.tripoin.xwp.data.dto.common.DTOGeraiMikrotik;
import com.tripoin.xwp.data.entity.EntityGeraiMikrotik;
import com.tripoin.xwp.rest.mikrotik.MikrotikConnection;
import com.tripoin.xwp.rest.repository.RepoGeraiMikrotik;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public abstract class ABaseCommand {

    @Autowired
    protected RepoGeraiMikrotik repoGeraiMikrotik;

    protected static MikrotikConnection mikrotikConnection;

    abstract String getMikrotikName();

    public MikrotikConnection getMikrotikConnection() {
        mikrotikConnection = new MikrotikConnection();
        DTOGeraiMikrotik dtoGeraiMikrotik = getDTOGeraiMikrotik(getMikrotikName());
        if(dtoGeraiMikrotik != null){
            mikrotikConnection.getInstance(getDTOGeraiMikrotik(getMikrotikName()));
            return mikrotikConnection;
        }else{
            return null;
        }
    }

    private DTOGeraiMikrotik getDTOGeraiMikrotik(String p_Code){
        DTOGeraiMikrotik dtoGeraiMikrotik = new DTOGeraiMikrotik();
        EntityGeraiMikrotik entityGeraiMikrotik = repoGeraiMikrotik.selectGeraiMikrotikByCode(p_Code);
        if(entityGeraiMikrotik != null){
            dtoGeraiMikrotik.setIpAddress(entityGeraiMikrotik.getIpAddress());
            dtoGeraiMikrotik.setUserName(entityGeraiMikrotik.getUserName());
            dtoGeraiMikrotik.setPassword(entityGeraiMikrotik.getPassword());
            dtoGeraiMikrotik.setDescription(entityGeraiMikrotik.getDescription());
        }
        return dtoGeraiMikrotik;
    }
}
