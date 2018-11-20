package com.videoShop.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.videoShop.product.Product;
import com.videoShop.product.ProductID;

@Configuration
@EnableJpaRepositories(basePackages = {"com.videoShop.product"})
public class ProductConfig extends RepositoryRestConfigurerAdapter {

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Product.class, ProductID.class);
    }
}
