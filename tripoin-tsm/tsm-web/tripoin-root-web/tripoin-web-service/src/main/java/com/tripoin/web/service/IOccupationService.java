package com.tripoin.web.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IOccupationService {

    public OccupationData getOccupation();
    
    public List<OccupationData> getAllOccupationDatas();
    
    public OccupationTransferObject getAllOccupationDatas(OccupationTransferObject occupationTransferObject);
    
    public GeneralTransferObject updateOccupation(OccupationTransferObject dataTransferObject, final ServletContext servletContext);
    
    public GeneralTransferObject saveOccupation(OccupationData occupationData, final ServletContext servletContext);

	public OccupationTransferObject deleteOccupation(List<OccupationData> occupationDatas, final ServletContext servletContext);

}
