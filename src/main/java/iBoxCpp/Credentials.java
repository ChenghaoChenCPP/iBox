package iBoxCpp;

public final class Credentials {
	/*Please uses aws s3 access key ID and secret access key
	 * To obtain please to go aws -> IAM -> Users
	 */
	public static String access_key_id = "AKIAJXP7MXI6OAIKDW4A";
	public static String secret_access_key = "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";

	public Credentials() {
	}

	public boolean checkCredentials() {
		if (access_key_id.length() == 0 || secret_access_key.length() == 0) {
			 System.out.println("Please fill in access_key_id and secret_access_key in Credentials.java");
			 return false;
		}
		return true;
	}

	public static String getAccess_key_id() {
		return access_key_id;
	}

	public static void setAccess_key_id(String access_key_id) {
		Credentials.access_key_id = access_key_id;
	}

	public static String getSecret_access_key() {
		return secret_access_key;
	}

	public static void setSecret_access_key(String secret_access_key) {
		Credentials.secret_access_key = secret_access_key;
	}
}

