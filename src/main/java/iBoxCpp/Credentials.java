package iBoxCpp;

public final class Credentials {
	/*Please uses aws s3 access key ID and secret access key
	 * To obtain please to go aws -> IAM -> Users
	 */

	private static String access_key_id = "AKIAJFEVZHQ2CQVQUGPA";
	private static String secret_access_key = "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";

	public Credentials() {
	}
	/*
	 * check if access_key_id or secret_access_key is empty string
	 */
	public boolean checkCredentials() {
		int idLength = access_key_id.length();
		int secretLength = secret_access_key.length();
		if (idLength == 0 || secretLength == 0) {
			 System.out.println("Please fill in access_key_id and secret_access_key in Credentials.java");
			 return false;
		}
		return true;
	}

	public String getAccess_key_id() {
		return access_key_id;
	}

	public void setAccess_key_id(final String access_key_id) {
		Credentials.access_key_id = access_key_id;
	}

	public String getSecret_access_key() {
		return secret_access_key;
	}

	public void setSecret_access_key(final String secret_access_key) {
		Credentials.secret_access_key = secret_access_key;
	}
}

