package com.tripoin.xwp.rest.service;

import com.tripoin.xwp.data.dto.common.DTOGeraiMikrotik;

import java.util.List;
import java.util.Map;

/**
 * Created by Ladies Man on 2/27/2016.
 */
public interface IServiceMikrotik {

    DTOGeraiMikrotik selectGeraiMikrotikByCode(String p_Code);

    List<Map<String, String>> getAllInterfaces(String p_MikrotikName);

}
