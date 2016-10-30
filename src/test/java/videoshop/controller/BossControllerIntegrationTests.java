/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package videoshop.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import videoshop.AbstractIntegrationTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.ExtendedModelMap;

/**
 * Integration tests for {@link BossController} that interact with the controller directly.
 * 
 * @author Oliver Gierke
 */
public class BossControllerIntegrationTests extends AbstractIntegrationTests {

	@Autowired BossController controller;

	/**
	 * Does not use any authentication and should raise a security exception.
	 */
	@Test(expected = AuthenticationException.class)
	public void rejectsUnauthenticatedAccessToController() {
		controller.customers(new ExtendedModelMap());
	}

	/**
	 * Uses {@link WithMockUser} to simulate access by a user with boss role.
	 */
	@WithMockUser(authorities = "BOSS")
	public void allowsAuthenticatedAccessToController() {

		ExtendedModelMap model = new ExtendedModelMap();

		controller.customers(model);

		assertThat(model.get("customerList"), is(notNullValue()));
	}
}
