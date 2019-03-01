package iBoxCpp;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Handler {
    private Credentials credentials;
    private BasicAWSCredentials awsCreds;
    private AmazonS3Client s3Client;
    private String bucketName;

    public S3Handler() {
        credentials = new Credentials();
	}

	/*
	 * checks if credentials are valid
	 * After it creates bucket in S3 if it's missing
	 */

	public final boolean checkS3Info() {
		if (!credentials.checkCredentials()) {
			return false;
		}
		String accKeyId = credentials.getAccess_key_id();
		String secretAccKey = credentials.getSecret_access_key();
		awsCreds = new BasicAWSCredentials(accKeyId, secretAccKey);
		s3Client = new AmazonS3Client(awsCreds);
		return checkBucket();
	}

	/*
	 * Creates bucket if it is not exist
	 */
	private boolean checkBucket() {
		String systemName = System.getProperty("user.name").toLowerCase();
		bucketName = "ibox-app-" + systemName;
		if (!s3Client.doesBucketExist(bucketName)) {
			s3Client.createBucket(bucketName, "us-west-1");
		}
		return true;
	}

	/*
	 * updates bucket on S3 according to the event
	 */

	public boolean updateBucket(final WatchEvent.Kind<?> kind, final Path eventDir, final String fileName, final String directory) {
		if (kind == ENTRY_CREATE || kind == ENTRY_MODIFY) {
			if (createEntry(eventDir, fileName, directory)) {
				System.out.println("Successfully uploaded " + fileName);
				return true;
			}
			System.out.println("Error when upload file on drive");
		} else {
			if (deleteEntry(eventDir, fileName, directory)) {
				System.out.println("Successfully deleted " + fileName);
				return true;
			}
			System.out.println("Error when delete file on drive");
		}
		return false;
	}

	/*
	 * creates file on s3
	 */
	private boolean createEntry(final Path eventDir, final String fileName, final String directory) {
		File file = new File(eventDir + "/" + fileName);
		String newfileName = AWSfileNameGenerator(eventDir, fileName, directory);
		PutObjectRequest response = new PutObjectRequest(bucketName, newfileName, file);
		s3Client.putObject(response);
		return true;
	}

	/*
	 * removes file on s3
	 */
	private boolean deleteEntry(final Path eventDir, final String fileName, final String directory) {
		String newFileName = AWSfileNameGenerator(eventDir, fileName, directory);
		 s3Client.deleteObject(new DeleteObjectRequest(bucketName, newFileName));
		 return true;
	}

	/*
	 * generates valid path like './directory/fileName'
	 */
	private String AWSfileNameGenerator(final Path eventDir, final String fileName, final String directory) {
		String eventDirectory = eventDir.toString();
		if (eventDirectory.equals(directory)) {
			return fileName;
		}
		return eventDirectory.substring(directory.length() + 1, eventDirectory.length()) + "/" + fileName;
	}
}
