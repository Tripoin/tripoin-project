package com.tripoin.xwp.rest.mikrotik.command;

import java.util.List;
import java.util.Map;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public interface IInquiryCommand {

    List<Map<String, String>> getAllPropertiesFromInterfaces();

    /**
     * Select a property from all connected interfaces
     * @param p_InterfaceProperty see application constant
     * @return
     *
     * @see com.tripoin.xwp.rest.constant.ApplicationConstant.MIKROTIK.INTERFACES
     */
    List<String> getSinglePropertyFromInterfaces(String p_InterfaceProperty);


}
