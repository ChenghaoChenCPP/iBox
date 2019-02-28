package iBoxCpp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CredentialsTest {
	
	private static Credentials c;
	
	@BeforeClass
	public static void init() {
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
	
//	@Test
//	public void checkCredentialWithNonEmptyStringTest() {
//		c.setAccess_key_id("AAAAAAAAAAAAAAAAAAAA");
//		c.setSecret_access_key("BBBBBBBBBBBBBBBBBBBB");
//		assertTrue(c.checkCredentials());
//	}
	
	@Test
	public void checkCredentialWithRealKeys() {
		c.setAccess_key_id("AKIAJFEVZHQ2CQVQUGPA");
		c.setSecret_access_key("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");
		assertTrue(c.checkCredentials());
	}
	
}
