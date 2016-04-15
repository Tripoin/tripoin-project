package com.tripoin.dto.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.tripoin.dto.app.GeneralPagingTransferObject;
import com.tripoin.dto.app.ProductData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponseListProduct extends GeneralPagingTransferObject {

    @SerializedName("productDatas")
	List<ProductData> productDatas;

	public List<ProductData> getProductDatas() {
		return productDatas;
	}

	public void setProductDatas(List<ProductData> productDatas) {
		this.productDatas = productDatas;
	}
}
