package com.tripoin.web.service;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IAreaService {
    
    public AreaTransferObject getAllAreaDatas(GeneralTransferObject generalTransferObject);
    
    public GeneralTransferObject updateArea(AreaTransferObject dataTransferObject, final ServletContext servletContext);
    
    public GeneralTransferObject saveArea(AreaTransferObject dataTransferObject, final ServletContext servletContext);

	public AreaTransferObject deleteArea(AreaTransferObject dataTransferObject, final ServletContext servletContext);

}
