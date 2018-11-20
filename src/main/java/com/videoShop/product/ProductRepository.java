package com.videoShop.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ProductProjection.class)
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
    static final String FIND_QUERY = "select p from Product p left join p.productIds pids where "
            + "p.title like :id% "
            + "or pids.asin like :id% "
            + "or pids.gtin like :id% "
            + "or pids.isbn like :id%";
    
    @Query(FIND_QUERY)
    @RestResource(path = "startsWith", rel = "startsWith")
    Product findProduct(@Param("id") String id);
}