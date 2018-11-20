package com.videoShop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videoShop.common.domains.Language;
import com.videoShop.common.domains.LanguageRepository;
import com.videoShop.common.exceptions.HostException;
import com.videoShop.productSite.ProductSiteRepository;
import com.videoShop.video.Video;
import com.videoShop.video.VideoRepository;

/**
 * 
 */
@RestController
public class SearchVideo {

	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private ProductSiteRepository productSiteRepository;

	@RequestMapping(value = "/api/videos", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<PagedResources<Resource<Video>>>  videos(@RequestParam(value = "id", defaultValue = "-1") String id,
			@RequestParam(value = "locale", defaultValue = "null") String lang, HttpServletRequest request, 
			PagedResourcesAssembler<Video> assembler, Pageable pageable) {
		String host = request.getRequestURL().toString();
		productSiteRepository.findHost(host).orElseThrow(() -> new HostException("Host is not registered."));
		if (lang.equals("null")){
			lang = request.getLocale().getISO3Language();
		}
		Language language = languageRepository.findByName(lang);
		Page<Video> videos = videoRepository.findVideos(id, language, pageable);

		return new ResponseEntity<>(assembler.toResource(videos), HttpStatus.OK);
	}
}
