package com.videoShop.productSite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.videoShop.productSite.ProductSite;

@Configuration
@EnableJpaRepositories(basePackages = {"com.videoShop.productSite"})
public class ProductSiteConfig extends RepositoryRestConfigurerAdapter{

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ProductSite.class);
    }
}
