package iBoxCpp;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class S3Handler {
	Credentials credentials;
	BasicAWSCredentials awsCreds;
	AmazonS3Client s3Client;
	String bucketName;
	
	public S3Handler() {
		credentials = new Credentials();
	}
	
	public boolean checkS3Info() {
		if(!credentials.checkCredentials())
			return false;
		awsCreds = new BasicAWSCredentials(credentials.access_key_id, credentials.secret_access_key);
		s3Client = new AmazonS3Client(awsCreds);
		return checkBucket();
	}
	
	private boolean checkBucket() {
		bucketName = "ibox-app-"+System.getProperty("user.name").toLowerCase();
		if(!s3Client.doesBucketExist(bucketName)) {
			s3Client.createBucket(bucketName,"us-west-1");
		}
		return true;
	}
	
	public boolean updateBucket(WatchEvent.Kind<?> kind, Path eventDir, String fileName, String directory) {
		if(kind == ENTRY_CREATE || kind == ENTRY_MODIFY) {
			if(createEntry(eventDir, fileName, directory)) {
				System.out.println("Successfully uploaded "+fileName);
				return true;
			}
			System.out.println("Error when upload file on drive");	
		}
		else {
			if(deleteEntry(eventDir, fileName, directory)) {
				System.out.println("Successfully deleted "+fileName);
				return true;
			}
			System.out.println("Error when delete file on drive");
		}
		return false;
	}
	
	private boolean createEntry(Path eventDir, String fileName, String directory) {
		File file = new File(eventDir+"/"+fileName);
		fileName = AWSfileNameGenerator(eventDir, fileName, directory);
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
		return true;
	}
	
	private boolean deleteEntry(Path eventDir, String fileName, String directory) {
		fileName = AWSfileNameGenerator(eventDir, fileName, directory);
		 s3Client.deleteObject(new DeleteObjectRequest(bucketName,fileName));
		 return true;
	}
	
	private String AWSfileNameGenerator(Path eventDir, String fileName, String directory) {
		String eventDirectory = eventDir.toString();
		if(eventDirectory.equals(directory)) {
			return fileName;
		}
		return eventDirectory.substring(directory.length()+1, eventDirectory.length())+"/"+fileName;
	}
}
