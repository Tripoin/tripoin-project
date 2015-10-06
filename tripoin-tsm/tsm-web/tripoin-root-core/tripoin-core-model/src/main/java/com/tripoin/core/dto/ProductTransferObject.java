package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProductTransferObject extends GeneralTransferObject {
	
	private List<ProductData> productDatas;

	public List<ProductData> getProductDatas() {
		return productDatas;
	}

	public void setProductDatas(List<ProductData> productDatas) {
		this.productDatas = productDatas;
	}

	@Override
	public String toString() {
		return "ProductTransferObject [productDatas=" + productDatas + "]";
	}
	
}
