package iBoxCpp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CredentialsTest {
	
	private Credentials c;
	
	@Before
	public void init() {
		 c = new Credentials();
	}
	
	@Test
	public void checkCredentialWithEmptyAccessKeyIDANDSecretKeyAccessTest() {
		c.setAccess_key_id("");
		c.setSecret_access_key("");
		assertFalse(c.checkCredentials());
	}
	
	@Test
	public void checkCredentialWithEmptyAccessKeyID() {
		c.setAccess_key_id("");
		c.setSecret_access_key("BBBBBBBBBBBBBBBBBBBB");
		assertFalse(c.checkCredentials());
	}
	
	@Test
	public void checkCredentialWithEmptySecretAccessKey() {
		c.setAccess_key_id("AAAAAAAAAAAAAAAAAAAA");
		c.setSecret_access_key("");
		assertFalse(c.checkCredentials());
	}
	
	@Test
	public void checkCredentialWithNonEmptyStringTest() {
		c.setAccess_key_id("AAAAAAAAAAAAAAAAAAAA");
		c.setSecret_access_key("BBBBBBBBBBBBBBBBBBBB");
		assertTrue(c.checkCredentials());
	}
	
	

}
