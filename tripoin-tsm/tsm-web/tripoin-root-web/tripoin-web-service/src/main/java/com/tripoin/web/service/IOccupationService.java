package com.tripoin.web.service;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IOccupationService {
    
    public OccupationTransferObject getAllOccupationDatas(GeneralPagingTransferObject generalPagingTransferObject);
    
    public GeneralTransferObject updateOccupation(GeneralTransferObject dataTransferObject, final ServletContext servletContext);

}
