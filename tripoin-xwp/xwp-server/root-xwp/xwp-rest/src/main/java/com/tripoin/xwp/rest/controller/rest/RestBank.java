package com.tripoin.xwp.rest.controller.rest;

import com.tripoin.xwp.data.dto.request.DTORequestRestBank;
import com.tripoin.xwp.data.dto.response.DTOResponseBank;
import com.tripoin.xwp.rest.constant.ApplicationConstant.REST;
import com.tripoin.xwp.rest.constant.ApplicationConstant.REST.MANAGE_BANK;
import com.tripoin.xwp.rest.service.IServiceBank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
@RestController
@RequestMapping(value = REST.REST_PATH)
public class RestBank {

    @Autowired
    IServiceBank iServiceBank;

    Logger logger = LoggerFactory.getLogger(RestBank.class);

    @RequestMapping(value = MANAGE_BANK.SELECT_BY_CODE, method = RequestMethod.POST)
    @ResponseBody
    public List<DTOResponseBank> loadBankByCode(@RequestBody DTORequestRestBank dtoRequestRestBank){
        logger.info("INFO", "Requested Code : ".concat(dtoRequestRestBank.getCode()));
        return iServiceBank.selectBanksByCode(dtoRequestRestBank.getCode());
    }
}
