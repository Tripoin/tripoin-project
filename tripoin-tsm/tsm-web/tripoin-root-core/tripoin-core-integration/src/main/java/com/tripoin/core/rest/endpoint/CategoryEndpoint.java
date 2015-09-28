package com.tripoin.core.rest.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.CategoryData;
import com.tripoin.core.dto.CategoryTransferObject;
import com.tripoin.core.pojo.Category;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("categoryEndpoint")
public class CategoryEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(CategoryEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Secured({RoleConstant.ROLE_SUPERADMIN, RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	public Message<CategoryTransferObject> getAllCategory(Message<?> inMessage){	
		CategoryTransferObject categoryTransferObject = new CategoryTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<Category> categoryList = iGenericManagerJpa.loadObjects(Category.class);
			List<CategoryData> categoryDatas = new ArrayList<CategoryData>();
			if(categoryList != null){
				for(Category category : categoryList){
					CategoryData categoryData = new CategoryData(category);
					categoryDatas.add(categoryData);
				}
				categoryTransferObject.setCategoryDatas(categoryDatas);
			}
			categoryTransferObject.setResponseCode("0");
			categoryTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			categoryTransferObject.setResponseDesc("Load Category Data Success");			
		}catch (Exception e){
			LOGGER.error("Category System Error : "+e.getLocalizedMessage(), e);
			categoryTransferObject.setResponseCode("1");
			categoryTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			categoryTransferObject.setResponseDesc("Category System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(categoryTransferObject, responseHeaderMap);
		Message<CategoryTransferObject> message = new GenericMessage<CategoryTransferObject>(categoryTransferObject, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(CategoryTransferObject categoryTransferObject, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", categoryTransferObject.getResponseCode());
		responseHeaderMap.put("Return-Status-Msg", categoryTransferObject.getResponseDesc());
	}
	
}
