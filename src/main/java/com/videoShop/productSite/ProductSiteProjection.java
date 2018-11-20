package com.videoShop.productSite;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="productSite", types={ProductSite.class})
public interface ProductSiteProjection {
	
	Long getId();
	Boolean getEnabled();
	String getHost();
	
}
