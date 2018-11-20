package com.videoShop.productSite;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ProductSiteProjection.class)
public interface ProductSiteRepository extends PagingAndSortingRepository<ProductSite, Long>{
	static final String FIND_QUERY = "select p from ProductSite p where "
            + "p.host = :host and p.deleted = false and p.enabled = true ";
    
    @Query(FIND_QUERY)
    @RestResource(path = "startsWith", rel = "startsWith")
    Optional<ProductSite> findHost(@Param("host") String host);
}
