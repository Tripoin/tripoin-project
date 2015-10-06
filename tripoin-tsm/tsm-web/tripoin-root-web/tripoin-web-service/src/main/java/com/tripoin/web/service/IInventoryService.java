package com.tripoin.web.service;

import java.util.List;

import com.tripoin.core.dto.AvailabilityData;
import com.tripoin.core.dto.CategoryData;
import com.tripoin.core.dto.ProductData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IInventoryService {

    public List<ProductData> getAllProducts();

    public List<CategoryData> getAllCategories();
    
    public List<AvailabilityData> getAllAvailability();

    public void updateProduct(ProductData p);

    public void deleteProduct(int productId);

    public ProductData getProductById(int productId);

}
