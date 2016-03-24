package com.tripoin.core.rest.endpoint.upload;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.rest.endpoint.XReturnStatus;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("uploadEndpoint")
public class UploadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(UploadEndpoint.class);
	
	@Value("${path.image}")
	private String rootPath;
	
	private String fileName;

	@SuppressWarnings("rawtypes")
	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<GeneralTransferObject> doUpload(LinkedMultiValueMap<String, Object> multipartRequest){		
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			for (String elementName : multipartRequest.keySet()) {
				if (elementName.equals("tripoin")){
					LinkedList value =  (LinkedList)multipartRequest.get("tripoin");
					String[] multiValues = (String[]) value.get(0);
					for (String valueName : multiValues) {
						LOGGER.info(elementName + " - " + valueName);
					}
				} else if (elementName.equals("tripoin-upload")){
					fileName = ((UploadedMultipartFile) multipartRequest.getFirst("tripoin-upload")).getOriginalFilename();
					((UploadedMultipartFile) multipartRequest.getFirst("tripoin-upload")).transferTo(new File(rootPath.concat(fileName)));
				}
			}
			generalTransferObject.setResponseCode("0");
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			generalTransferObject.setResponseDesc("Upload Success : ".concat(fileName));			
		}catch (Exception e){
			e.printStackTrace();
			generalTransferObject.setResponseCode("1");
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc("Upload System Error : "+e.getMessage());
		}
		
		setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
		return message;	
	}
	
}
