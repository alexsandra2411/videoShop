package com.videoShop.common.domains;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface LanguageRepository extends PagingAndSortingRepository<Language, Long>{

	@RestResource(path = "startsWith", rel = "startsWith")
    Language findByName(String name);
    
}
