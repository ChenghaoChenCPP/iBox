package iBoxCpp;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class S3HandlerTest {
	
	private S3Handler s3Test;
	private Credentials cTest;
	//private BasicAWSCredentials awsCredsTest;
	private AmazonS3Client s3ClientTest;
	
	@Before
	public void init() {
		s3ClientTest = Mockito.mock(AmazonS3Client.class);
		s3Test = new S3Handler();
		cTest = new Credentials();
		
	}
	
	@Test
	public void checkS3InfoTestWithCredential() {
		cTest.setAccess_key_id("AAAAAAAAA");
		cTest.setSecret_access_key("BBBBBBBBBBBB");
		Mockito.when(s3ClientTest.doesBucketExist("ibox-app")).thenReturn(true);
		assertTrue(s3Test.checkS3Info());
	}
	
	@Test
	public void updateBucketTestCreate() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_CREATE, pathTest , "", "")).thenReturn(true);
		assertTrue(s3updateBucketTest.updateBucket(ENTRY_CREATE, pathTest , "",""));
	}
	
	@Test
	public void updateBucketTestCreateErrorMessage() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_CREATE, pathTest , "", "")).thenReturn(false);
		assertFalse(s3updateBucketTest.updateBucket(ENTRY_CREATE, pathTest , "",""));
	}
	
	@Test
	public void updateBucketTestDelete() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_DELETE, pathTest , "", "")).thenReturn(true);
		assertTrue(s3updateBucketTest.updateBucket(ENTRY_DELETE, pathTest , "",""));
	}
	
	@Test
	public void updateBucketTestDeleteErrorMessage() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_DELETE, pathTest , "", "")).thenReturn(false);
		assertFalse(s3updateBucketTest.updateBucket(ENTRY_DELETE, pathTest , "",""));
	}
	
	@Test
	public void updateBucketTestModify() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_MODIFY, pathTest , "", "")).thenReturn(true);
		assertTrue(s3updateBucketTest.updateBucket(ENTRY_MODIFY, pathTest , "",""));
	}
	
	@Test
	public void updateBucketTestModifyErrorMessage() {
		S3Handler s3updateBucketTest = Mockito.mock(S3Handler.class);
		Path pathTest = Paths.get("./");
		Mockito.when(s3updateBucketTest.updateBucket(ENTRY_MODIFY, pathTest , "", "")).thenReturn(false);
		assertFalse(s3updateBucketTest.updateBucket(ENTRY_MODIFY, pathTest , "",""));
	}

}
