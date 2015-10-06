package com.tripoin.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.AvailabilityData;
import com.tripoin.core.dto.AvailabilityTransferObject;
import com.tripoin.core.dto.CategoryData;
import com.tripoin.core.dto.CategoryTransferObject;
import com.tripoin.core.dto.ProductData;
import com.tripoin.core.dto.ProductTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IInventoryService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	@Override
	public List<ProductData> getAllProducts() {
		ProductTransferObject productConnectionDTO = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_PRODUCT), ProductTransferObject.class);
		return productConnectionDTO.getProductDatas();
	}

	@Override
	public List<CategoryData> getAllCategories() {
		CategoryTransferObject categoryTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_CATEGORY), CategoryTransferObject.class);
		return categoryTransferObject.getCategoryDatas();
	}
	
	@Override
	public List<AvailabilityData> getAllAvailability(){
		AvailabilityTransferObject availabilityTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_AVAILABILITY), AvailabilityTransferObject.class);
		return availabilityTransferObject.getAvailabilityDatas();		
	}

	@Override
	public void updateProduct(ProductData p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductData getProductById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
