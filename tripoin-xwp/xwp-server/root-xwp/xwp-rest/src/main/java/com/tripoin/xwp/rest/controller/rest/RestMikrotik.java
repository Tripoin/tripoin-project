package com.tripoin.xwp.rest.controller.rest;

import com.tripoin.xwp.data.dto.request.DTORequestMikrotik;
import com.tripoin.xwp.rest.constant.ApplicationConstant;
import com.tripoin.xwp.rest.constant.ApplicationConstant.REST;
import com.tripoin.xwp.rest.constant.ApplicationConstant.REST.MANAGE_MIKROTIK;
import com.tripoin.xwp.rest.service.IServiceMikrotik;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
@RestController
@RequestMapping(value = REST.REST_PATH)
public class RestMikrotik {

    @Autowired
    IServiceMikrotik iServiceMikrotik;

    Logger logger = LoggerFactory.getLogger(RestMikrotik.class);

    @RequestMapping(value = MANAGE_MIKROTIK.GET_ALL_INTERFACES, method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, String>> loadAllInterfaceMikrotik(@RequestBody DTORequestMikrotik p_DtoRequestMikrotik){
        logger.info(ApplicationConstant.LOG.INFO, p_DtoRequestMikrotik.toString());
        return iServiceMikrotik.getAllInterfaces(p_DtoRequestMikrotik.getMikrotikName());
    }
}
