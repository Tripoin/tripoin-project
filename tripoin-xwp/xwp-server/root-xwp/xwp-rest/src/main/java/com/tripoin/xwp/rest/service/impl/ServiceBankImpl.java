package com.tripoin.xwp.rest.service.impl;

import com.tripoin.xwp.data.dto.response.DTOResponseBank;
import com.tripoin.xwp.data.entity.EntityBank;
import com.tripoin.xwp.rest.constant.ApplicationConstant;
import com.tripoin.xwp.rest.repository.RepoBank;
import com.tripoin.xwp.rest.service.IServiceBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ladies Man on 2/27/2016.
 */
@Service(value = ApplicationConstant.SPRING_COMPONENT.SERVICE.SERVICE_BANK)
public class ServiceBankImpl implements IServiceBank {

    @Autowired
    RepoBank repoBank;
    @Override
    public List<DTOResponseBank> selectBanksByCode(String p_Code) {
        List<EntityBank> entityBanks = repoBank.selectBanksByCode(p_Code);
        List<DTOResponseBank> dtoResponseBanks = new ArrayList<>();
        for(EntityBank entityBank : entityBanks){
            DTOResponseBank dtoResponseBank = new DTOResponseBank();
            dtoResponseBank.setId(entityBank.getId());
            dtoResponseBank.setCode(entityBank.getCode());
            dtoResponseBank.setName(entityBank.getName());
            dtoResponseBanks.add(dtoResponseBank);
        }
        return dtoResponseBanks;
    }
}
