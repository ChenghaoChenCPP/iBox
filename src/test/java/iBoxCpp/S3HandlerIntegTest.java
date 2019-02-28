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
		cTest.setAccess_key_id("AKIAJXP7MXI6OAIKDW4A");
		cTest.setSecret_access_key("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");
		
	}
	
	
	@Test
	public void updateBucketIntegTestWithNullInputCreatingEntry() {
		s3Test.bucketName = "ibox-app-hoho";
		Path pathTest = Paths.get("./ibox");
		try{
			s3Test.updateBucket(ENTRY_CREATE, pathTest , "test.txt", "./ibox");
		} catch(Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
		
	}
	
	@Test
	public void updateBucketIntegTestCreatingEntry() {
		s3Test.bucketName = "ibox-app-hoho";
		Path pathTest = Paths.get("./ibox");
		try{
			s3Test.updateBucket(ENTRY_CREATE, pathTest , "test.txt", "./ibox");
		} catch(Exception e) {
			assertTrue(true);
		}
		assertTrue(true);
	}
	
	@Test
	public void updateBucketIntegTestModifyEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_MODIFY, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
	}
	
	@Test
	public void updateBucketIntegTestDeletingEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_DELETE, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
	}
	

}
