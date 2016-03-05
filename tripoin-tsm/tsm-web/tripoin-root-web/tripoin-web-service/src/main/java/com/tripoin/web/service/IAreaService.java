package com.tripoin.web.service;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IAreaService {
    
    public AreaTransferObject getAllAreaDatas(GeneralPagingTransferObject generalPagingTransferObject);
    
    public GeneralTransferObject updateArea(GeneralTransferObject dataTransferObject, final ServletContext servletContext);
    
    public GeneralTransferObject saveArea(GeneralTransferObject dataTransferObject, final ServletContext servletContext);

	public GeneralTransferObject deleteArea(GeneralTransferObject dataTransferObject, final ServletContext servletContext);

}
