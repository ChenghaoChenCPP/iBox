package iBoxCpp;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3HandlerIntegTest {
	
	private S3Handler s3Test;
	private Credentials cTest;
	
	@Before
	public void init() {
		s3Test = new S3Handler();
		s3Test.checkS3Info();
		cTest = new Credentials();
		cTest.setAccess_key_id("AKIAJFEVZHQ2CQVQUGPA");
		cTest.setSecret_access_key("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");
		
	}
	
	
	@Test
	public void updateBucketIntegTest1WithNullInputCreatingEntry() {;
		Path pathTest = Paths.get("");
		try{
			s3Test.updateBucket(ENTRY_CREATE, pathTest , "test.txt", "./ibox");
			assertTrue(false);
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void updateBucketIntegTest2CreatingEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_CREATE, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
	}
	
	@Test
	public void updateBucket3IntegTestModifyEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_MODIFY, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
	}
	
	@Test
	public void updateBucket4IntegTestDeletingEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_DELETE, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
	}
	

}
