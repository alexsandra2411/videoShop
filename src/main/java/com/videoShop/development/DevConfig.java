package com.videoShop.development;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.videoShop.common.domains.Language;
import com.videoShop.common.domains.LanguageRepository;
import com.videoShop.product.Product;
import com.videoShop.product.ProductID;
import com.videoShop.product.ProductIDRepository;
import com.videoShop.product.ProductRepository;
import com.videoShop.productSite.ProductSite;
import com.videoShop.productSite.ProductSiteRepository;
import com.videoShop.video.Video;
import com.videoShop.video.VideoRepository;

/**
 * Thats allow you to insert some data into DB for development or testing purpose.
 */
@Profile("dev")
@Configuration
public class DevConfig {
	
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private ProductIDRepository productIDRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private ProductSiteRepository productSiteRepository;

	/**
	 * Post construct initialization. Creates Groups and Roles if not exists.
	 */
	@PostConstruct
	public void init() {
		Language language = new Language("eng");
    	languageRepository.save(language);
    	language = new Language("rus");
    	languageRepository.save(language);
    	
    	Product product = new Product();
    	product.setTitle("Title_1");
    	product.setPrice(240d);
    	product.setEnabled(true);
    	productRepository.save(product);
    	
    	ProductID productID = new ProductID();
    	productID.setIsbn("3");
    	productID.setGtin("2");
    	productID.setAsin("1");
    	productID.setProduct(product);
    	productID.setEnabled(true);
    	productIDRepository.save(productID);
    	
    	Product product1 = new Product();
    	product1.setTitle("Title_2");
    	product1.setPrice(240d);
    	product1.setEnabled(true);
    	productRepository.save(product1);
    	    	
    	Video video = new Video();
    	video.setDeleted(false);
    	video.setEnabled(true);
    	video.setHelpful(1);
    	video.setHelpless(2);
    	video.setLanguage(language);
    	video.setProduct(product);
    	video.setRating(3);
    	video.setTitle("Video_1");
    	video.setVideoLink("http:\\LinkToVideo_1");
    	videoRepository.save(video);
    	
    	video = new Video();
    	video.setDeleted(false);
    	video.setEnabled(true);
    	video.setHelpful(1);
    	video.setHelpless(2);
    	video.setLanguage(language);
    	video.setProduct(product);
    	video.setRating(3);
    	video.setTitle("Video_2");
    	video.setVideoLink("http:\\LinkToVideo_2");
    	videoRepository.save(video);
    	
    	video = new Video();
    	video.setDeleted(true);
    	video.setEnabled(true);
    	video.setHelpful(1);
    	video.setHelpless(2);
    	video.setLanguage(language);
    	video.setProduct(product);
    	video.setRating(3);
    	video.setTitle("Video_2");
    	video.setVideoLink("http:\\LinkToVideo_3");
    	videoRepository.save(video);
    	
    	video = new Video();
    	video.setDeleted(false);
    	video.setEnabled(true);
    	video.setHelpful(1);
    	video.setHelpless(2);
    	video.setLanguage(language);
    	video.setProduct(product1);
    	video.setRating(3);
    	video.setTitle("Video_2");
    	video.setVideoLink("http:\\LinkToVideo_4");
    	videoRepository.save(video);
    	
    	ProductSite productSite = new ProductSite();
    	productSite.setHost("http://localhost:8080/api/videos");
    	productSite.setEnabled(true);
    	productSiteRepository.save(productSite);
		
	}
}
