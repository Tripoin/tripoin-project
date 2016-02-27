package com.tripoin.xwp.rest.service;

import com.tripoin.xwp.data.dto.response.DTOResponseBank;

import java.util.List;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public interface IServiceBank {

    List<DTOResponseBank> selectBanksByCode(String p_Code);

}
