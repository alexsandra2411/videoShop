package com.videoShop.common.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.videoShop.common.domains.Language;

@Configuration
@EnableJpaRepositories(basePackages = {"com.videoShop.common.domains"})
public class BaseConfig extends RepositoryRestConfigurerAdapter {
    
    /**
     * Init configuration
     */
    @PostConstruct
    public void init() {
    	
    }
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Language.class);
    }
    
    /**
     * Config H2DB Web Server Client
     * @return Server
     * @throws SQLException
     */
    @Profile("dev")
    @Bean(initMethod="start", destroyMethod="stop", name="h2WebServer")
    public Server h2WebServer() throws SQLException{
        return Server.createWebServer("-web,-webAllowOthers,-webPort,8082".split(","));
    }

}
