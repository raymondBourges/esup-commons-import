package org.esupportail.formation.domain;

import org.esupportail.formation.services.auth.Authenticator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AuthenticatorTest {
	private Authenticator authenticator;
	@Before
    public void setUp() throws Exception {
		String[] springFiles = { "classpath*:META-INF/testApplicationContext.xml" };
	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFiles);
        authenticator = (Authenticator) applicationContext.getBean("authenticator");
    }
	@Test
	public void testSetAuthenticationService() {
		Assert.assertEquals("org.esupportail.formation.services.auth.AuthenticatorImpl",authenticator.getClass().getName());
	}

}
