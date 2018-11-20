package com.videoShop.product;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "product", types = {Product.class})
public interface ProductProjection {
    
    Long getId();
    String getTitle();
    Double getPrice();
    Set<ProductID> getProductIds();
    Boolean getEnabled();
}
