package com.tripoin.web.service;

import java.util.List;

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
    
    public GeneralTransferObject updateOccupation(OccupationData occupationData);
    
    public GeneralTransferObject saveOccupation(OccupationData occupationData);

	public OccupationTransferObject deleteOccupation(List<OccupationData> occupationDatas);

}
