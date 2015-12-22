package com.tripoin.core.rest.endpoint;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.core.pojo.VersionControlSystemTable;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("paginationEndpoint")
public class PaginationEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(PaginationEndpoint.class);
	
	@Autowired
	private IVersionControlSystemTableService iVersionControlSystemTableService;

	public Message<GeneralPagingTransferObject> getPagination(Message<GeneralPagingTransferObject> inMessage){	
		GeneralPagingTransferObject paginationTransferObject = new GeneralPagingTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{			
			if(inMessage.getPayload() != null){
				VersionControlSystemTable versionControlSystemTable = iVersionControlSystemTableService.loadValue(inMessage.getPayload().getPageName());				
				if(versionControlSystemTable == null){
					LOGGER.error("Load Pagination System Error : Version Control System Table is Null");
					paginationTransferObject.setTotalRow(1);
					paginationTransferObject.setResponseCode("2");
					paginationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
					paginationTransferObject.setResponseDesc("Load Pagination System Error : Version Control System Table is Null");
				}else{
					paginationTransferObject.setTotalRow(versionControlSystemTable.getTotalRow().intValue());
					paginationTransferObject.setResponseCode("0");
					paginationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
					paginationTransferObject.setResponseDesc("Load Pagination Data Success");					
				}
				System.out.println(versionControlSystemTable.getTotalRow().intValue());
			}else{
				LOGGER.error("Load Pagination System Error : Parameter is Null");
				paginationTransferObject.setTotalRow(1);
				paginationTransferObject.setResponseCode("3");
				paginationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				paginationTransferObject.setResponseDesc("Load Pagination System Error : Parameter is Null");				
			}			
		}catch (Exception e){
			LOGGER.error("Load Pagination System Error : "+e.getLocalizedMessage(), e);
			paginationTransferObject.setResponseCode("1");
			paginationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			paginationTransferObject.setResponseDesc("Load Pagination System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(paginationTransferObject, responseHeaderMap);
		Message<GeneralPagingTransferObject> message = new GenericMessage<GeneralPagingTransferObject>(paginationTransferObject, responseHeaderMap);
		return message;		
	}
	
}
