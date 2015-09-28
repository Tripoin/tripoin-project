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
import com.tripoin.core.dto.ProductData;
import com.tripoin.core.dto.ProductTransferObject;
import com.tripoin.core.pojo.Product;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("productLoadEndpoint")
public class ProductLoadEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Secured({RoleConstant.ROLE_SUPERADMIN, RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	public Message<ProductTransferObject> getAllProduct(Message<?> inMessage){	
		ProductTransferObject productTransferObject = new ProductTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<Product> productList = iGenericManagerJpa.loadObjects(Product.class);
			List<ProductData> productDatas = new ArrayList<ProductData>();
			if(productList != null){
				for(Product product : productList){
					ProductData productData = new ProductData(product);
					productDatas.add(productData);
				}
				productTransferObject.setProductDatas(productDatas);
			}
			productTransferObject.setResponseCode("0");
			productTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			productTransferObject.setResponseDesc("Load Product Data Success");			
		}catch (Exception e){
			LOGGER.error("Load Product System Error : "+e.getLocalizedMessage(), e);
			productTransferObject.setResponseCode("1");
			productTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			productTransferObject.setResponseDesc("Load Product System Error : "+e.getLocalizedMessage());
		}
		
		setReturnStatusAndMessage(productTransferObject, responseHeaderMap);
		Message<ProductTransferObject> message = new GenericMessage<ProductTransferObject>(productTransferObject, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(ProductTransferObject productTransferObject, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", productTransferObject.getResponseCode());
		responseHeaderMap.put("Return-Status-Msg", productTransferObject.getResponseDesc());
	}
	
}
