package com.videoShop.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.videoShop.common.domains.Language;

@RepositoryRestResource(excerptProjection = VideoProjection.class)
public interface VideoRepository extends PagingAndSortingRepository<Video, Long>{
	static final String FIND_QUERY = "select v "
			+ "from Video v left join v.product p left join p.productIds pids "
			+ "WHERE (pids.asin = :id or pids.gtin = :id or pids.isbn = :id) "
			+ "and v.deleted = false and v.enabled = true and v.language = :language ";
			
	@Query(FIND_QUERY)
	@RestResource(path = "startsWith", rel = "startsWith")
	Page<Video> findVideos(@Param("id") String id, @Param("language") Language language, Pageable p);
	
}
