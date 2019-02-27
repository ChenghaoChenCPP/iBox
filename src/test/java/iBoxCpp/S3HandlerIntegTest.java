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
	
	private static S3Handler s3Test;
	
	@BeforeClass
	public static void init() {
		s3Test = new S3Handler();
		s3Test.checkS3Info();
	}
	
	@Test
	public void updateBucketIntegTestCreatingEntry() {
		Path pathTest = Paths.get("./ibox");
		boolean resTest = s3Test.updateBucket(ENTRY_CREATE, pathTest , "test.txt", "./ibox");
		assertTrue(resTest);
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
