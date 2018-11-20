package com.videoShop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ProductIDProjection.class, path="productids")
public interface ProductIDRepository extends PagingAndSortingRepository<ProductID, Long>{
    static final String FIND_QUERY = "select p from ProductID p where "
            + "p.asin like :name% "
            + "or p.gtin like :name% "
            + "or p.isbn like :name%";
    
    @Query(FIND_QUERY)
    @RestResource(path = "startsWith", rel = "startsWith")
    Page<ProductID> findStartsWith(@Param("name") String name, Pageable p);
}
