package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IAreaService {

    public AreaData getArea();
    
    public List<AreaData> getAllAreaDatas();
    
    public AreaTransferObject getAllAreaDatas(AreaTransferObject occupationTransferObject);
    
    public GeneralTransferObject updateArea(AreaData occupationData, final ServletContext servletContext);
    
    public GeneralTransferObject saveArea(AreaData occupationData, final ServletContext servletContext);

	public AreaTransferObject deleteArea(List<AreaData> occupationDatas, final ServletContext servletContext);

}
