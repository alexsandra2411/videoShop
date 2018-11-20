package com.videoShop;


import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
/**
 * Util helps to check security configurations.
 */
public class SecurityTestUtil {

	private final String apiUrl;
	private final String domain;
	private MockMvc mockMvc;

	public SecurityTestUtil(String apiUrl, String domain, MockMvc mockMvc) {
		this.apiUrl = apiUrl;
		this.domain = domain;
		this.mockMvc = mockMvc;
	}

	/**
	 * @see SecurityTestUtil#canNotCreate(String, String, String, String)
	 * @param body
	 * @throws Exception
	 */
	public void canNotCreate(String body) throws Exception {
		canNotCreate(body, null, null, null);
	}

	/**
	 * Checks that user can not create object.
	 * 
	 * @param body
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canNotCreate(String body, String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(create(body), user, role, password)).andExpect(isForbidden());
	}

	/**
	 * Checks that user can create object.
	 * 
	 * @param body
	 * @param user
	 * @param role
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public MvcResult canCreate(String body, String user, String role, String password) throws Exception {
		return mockMvc.perform(withUser(create(body), user, role, password)).andExpect(status().isCreated())
				.andReturn();
	}

	/**
	 * @see SecurityTestUtil#canGet(Long, String, String, String)
	 * @param id
	 * @throws Exception
	 */
	public void canGet(Long id) throws Exception {
		canGet(id, null, null, null);
	}

	/**
	 * Checks that user can read object
	 * 
	 * @param id
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canGet(Long id, String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(load(id), user, role, password)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber());
	}

	/**
	 * @see SecurityTestUtil#canNotGet(Long, String, String, String)
	 * @param id
	 * @throws Exception
	 */
	public void canNotGet(Long id) throws Exception {
		canNotGet(id, null, null, null);
	}
	
	/**
	 * Checks that user can not read object
	 * @param id
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canNotGet(Long id, String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(load(id), user, role, password)).andExpect(isForbidden());
	}

	/**
	 * @see SecurityTestUtil#canList(String, String, String)
	 * @throws Exception
	 */
	public void canList() throws Exception {
		canList(null, null, null);
	}

	/**
	 * Checks that user can list objects
	 * 
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canList(String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(list(), user, role, password)).andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded." + domain).isArray());
	}

	/**
	 * @see SecurityTestUtil#canNotList(String, String, String)
	 * @throws Exception
	 */
	public void canNotList() throws Exception {
		canNotList(null, null, null);
	}
	
	/**
	 * Checks that user can not list
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canNotList(String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(list(), user, role, password)).andExpect(isForbidden());
	}

	/**
	 * @see SecurityTestUtil#canNotUpdate(Long, String, String, String, String)
	 * @param id
	 * @param body
	 * @throws Exception
	 */
	public void canNotUpdate(Long id, String body) throws Exception{
		canNotUpdate(id, body, null, null, null);
	}
	
	/**
	 * Checks that user can not edit object
	 * @param id
	 * @param body
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canNotUpdate(Long id, String body, String user, String role, String password) throws Exception {
		mockMvc.perform(withUser(updatePatch(id, body), user, role, password)).andExpect(isForbidden());
		mockMvc.perform(withUser(updatePut(id, body), user, role, password)).andExpect(isForbidden());
	}

	/**
	 * @see SecurityTestUtil#canUpdate(Long, String, String, String, String)
	 * @param id
	 * @param body
	 * @throws Exception
	 */
	public void canUpdate(Long id, String body) throws Exception{
		canUpdate(id, body, null, null, null);
	}
	
	/**
	 * Checks that user can edit object
	 * @param id
	 * @param body
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canUpdate(Long id, String body, String user, String role, String password) throws Exception{
		mockMvc.perform(withUser(updatePatch(id, body), user, role, password)).andExpect(status().isOk());
	}
	/**
	 * @see SecurityTestUtil#canNotDelete(Long, String, String, String)
	 * @param id
	 * @throws Exception
	 */
	public void canNotDelete(Long id) throws Exception{
		canNotDelete(id, null, null, null);
	}
	
	/**
	 * Checks that user can not delete object
	 * @param id
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception
	 */
	public void canNotDelete(Long id, String user, String role, String password) throws Exception{
		mockMvc.perform(withUser(remove(id), user, role, password)).andExpect(isForbidden());
	}
	
	/**
	 * @see SecurityTestUtil#canDelete(Long, String, String, String)
	 * @param id
	 * @throws Exception 
	 */
	public void canDelete(Long id) throws Exception{
		canDelete(id, null, null, null);
	}
	
	/**
	 * Checks that user can delete object
	 * @param id
	 * @param user
	 * @param role
	 * @param password
	 * @throws Exception 
	 */
	public void canDelete(Long id, String user, String role, String password) throws Exception{
		mockMvc.perform(withUser(remove(id), user, role, password)).andExpect(status().is2xxSuccessful());
	}
	
	/**
	 * Helper that adds user.
	 * 
	 * @param builder
	 * @param user
	 * @param role
	 * @param password
	 * @return
	 */
	private MockHttpServletRequestBuilder withUser(MockHttpServletRequestBuilder builder, String user, String role,
			String password) {
		if (user != null) {
			UserRequestPostProcessor currentUser = user(user).password(password).roles(role);
			return builder.with(currentUser);
		}
		return builder;
	}

	/**
	 * Creates post request builder with given body.
	 * 
	 * @param body
	 * @return
	 */
	public MockHttpServletRequestBuilder create(String body) {
		return post(apiUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(body);
	}

	/**
	 * Create get request builder for given id
	 * 
	 * @param id
	 * @return
	 */
	public MockHttpServletRequestBuilder load(Long id) {
		return get(apiUrl + "/" + id);
	}

	/**
	 * create list request builder
	 * 
	 * @return
	 */
	public MockHttpServletRequestBuilder list() {
		return get(apiUrl);
	}

	/**
	 * Creates patch request builder
	 * 
	 * @param id
	 * @param body
	 * @return
	 */
	public MockHttpServletRequestBuilder updatePatch(Long id, String body) {
		return patch(apiUrl+"/" + id).contentType(MediaType.APPLICATION_JSON_VALUE).content(body);
	}

	/**
	 * Creates put request builder
	 * 
	 * @param id
	 * @param body
	 * @return
	 */
	public MockHttpServletRequestBuilder updatePut(Long id, String body) {
		return put(apiUrl+"/" + id).contentType(MediaType.APPLICATION_JSON_VALUE).content(body);
	}
	
	/**
	 * Creates delete request builder
	 * @param id
	 * @return
	 */
	public MockHttpServletRequestBuilder remove(Long id) {
		return delete(apiUrl+"/" + id);
	}
	
	/**
	 * Assert the response status code is in the 401 or 403.
	 */
	protected ResultMatcher isForbidden() {
		return new ResultMatcher() {
			@Override
			public void match(MvcResult result) throws Exception {
				assertTrue("Response status value " + result.getResponse().getStatus(),
						result.getResponse().getStatus() == 401 || result.getResponse().getStatus() == 403);
			}
		};
	}

}
