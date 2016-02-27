package com.tripoin.xwp.rest.mikrotik.command.impl;

import com.tripoin.xwp.rest.constant.ApplicationConstant.MIKROTIK.COMMAND;
import com.tripoin.xwp.rest.mikrotik.command.IInquiryCommand;
import me.legrange.mikrotik.MikrotikApiException;

import java.util.List;
import java.util.Map;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public abstract class MikrotikCommand extends ABaseCommand implements IInquiryCommand{

    @Override
    public List<Map<String, String>> getAllPropertiesFromInterfaces() {
        List<Map<String, String>> result = null;
        try {
            result = getMikrotikConnection().getApiConnection().execute(COMMAND.GET_ALL_PROPERTIES);
        } catch (MikrotikApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getSinglePropertyFromInterfaces(String p_InterfaceProperty) {
        List<String> result = null;
        List<Map<String, String>> allProperties = getAllPropertiesFromInterfaces();
        for(Map<String, String> allP : allProperties){
            result.add(allP.get(p_InterfaceProperty));
        }
        return result;
    }
}
