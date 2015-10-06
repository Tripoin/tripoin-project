package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class CategoryTransferObject extends GeneralTransferObject {
	
	private List<CategoryData> categoryDatas;

	public List<CategoryData> getCategoryDatas() {
		return categoryDatas;
	}

	public void setCategoryDatas(List<CategoryData> categoryDatas) {
		this.categoryDatas = categoryDatas;
	}

	@Override
	public String toString() {
		return "CategoryTransferObject [categoryDatas=" + categoryDatas + "]";
	}
	
}
