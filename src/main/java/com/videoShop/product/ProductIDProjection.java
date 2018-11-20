package com.videoShop.product;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "productId", types = {ProductID.class})
public interface ProductIDProjection {

    public Long getId();
    public String getAsin();
    public String getGtin();
    public String getIsbn();
    public Product getProduct();
    public Boolean getEnabled();
}
