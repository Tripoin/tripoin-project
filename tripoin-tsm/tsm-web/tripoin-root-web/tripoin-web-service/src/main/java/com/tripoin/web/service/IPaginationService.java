package com.tripoin.web.service;

import com.tripoin.core.dto.GeneralPagingTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IPaginationService {

    public GeneralPagingTransferObject getPagination(GeneralPagingTransferObject generalPagingTransferObject);

}
