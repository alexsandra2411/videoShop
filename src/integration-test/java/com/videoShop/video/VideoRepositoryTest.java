package com.videoShop.video;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.videoShop.VideoShopPublicApiApplication;
import com.videoShop.SecurityTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { VideoShopPublicApiApplication.class })
@ActiveProfiles("test")
@WebAppConfiguration
public class VideoRepositoryTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mockMvc;
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbc;

    private SecurityTestUtil securityUtil;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilters(springSecurityFilterChain).build();
        this.securityUtil = new SecurityTestUtil("/api/videos", "videos", mockMvc);
		this.jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("INSERT INTO LANGUAGE (id, name) VALUES (1, 'test')");
	}

	@After
	public void tearDown() {
		jdbc.execute("DELETE FROM Language WHERE id = 1");
	}

	@Test
	public void test() throws Exception {
		String body = "{\"title\":\"test\", \"videoLink\":\"test\", \"language\":\"/api/languages/1\"}";
		mockMvc.perform(get("/api/languages")).andExpect(status().isOk());
		mockMvc.perform(post("/api/languages").with(user("myuser").password("password"))
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(body)).andExpect(status().isForbidden());
	}
	
	//TODO: delete me. It's just an example

	/**
	 * It is just an example how to use SecuirtyTestUtil
	 * @throws Exception 
	 */
	@Test
	public void testExample() throws Exception{
	    String body = "{\"title\":\"test\", \"videoLink\":\"test\", \"language\":\"/api/languages/1\"}";
	    securityUtil.canList();
        securityUtil.canNotCreate(body);
        //create some video with id 1 here ....
	    securityUtil.canNotUpdate(1L, "{..some json..}", "user", "USER", "password");
        securityUtil.canNotDelete(1L);
	}

}
