package org.esupportail.formation.domain;

import org.esupportail.formation.services.auth.Authenticator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class AuthenticatorTest {
	@Autowired
	private Authenticator authenticator;
	@Before
    public void setUp() throws Exception {
		
	}
	@Test
	public void testSetAuthenticationService() {
		Assert.assertEquals("org.esupportail.formation.services.auth.AuthenticatorImpl",authenticator.getClass().getName());
	}
}
