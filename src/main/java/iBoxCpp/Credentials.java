package iBoxCpp;

public class Credentials {
	/*Please uses aws s3 access key ID and secret access key  
	 * To obtain please to go aws -> IAM -> Users
	 */
	public final static String access_key_id = ""; 
	public final static String secret_access_key = "";
	
	public Credentials() {
		
	}
	
	public boolean checkCredentials() {
		if(access_key_id.length()==0 || secret_access_key.length() ==0) {
			System.err.println("Please fill in access_key_id and secret_access_key in Credentials.java");
			return false;
		}
		return true;
	}

	public static String getAccessKeyId() {
		return access_key_id;
	}

	public static String getSecretAccessKey() {
		return secret_access_key;
	}
	
	
}
