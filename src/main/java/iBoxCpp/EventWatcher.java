package iBoxCpp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EventWatcher {
    //directory that the file locates
    private String directory;
	//path that the file locates
    private Path path;

    public EventWatcher() {
	}

    public final String getDirectory() {
		return directory;
	}

    public void setDirectory(final String directory) {
		this.directory = directory;
	}

	public final Path getPath() {
		return path;
	}

	/*
	 * check if the directory specified is an empty string
	 * if not, the program checks for if the directory exist
	 */
	public final boolean checkValidDriectory() {
		if (directory.equals("")) {
			System.out.println("Path is empty");
			return false;
		}

		path = Paths.get(directory);

		if (!Files.exists(path)) {
			return createDirectory(path);
		}

		return true;
	}

	/*
	 * check if the specified path can be created
	 */
	private boolean createDirectory(final Path path) {
		boolean created = new File(directory).mkdirs();
		if (!created) {
			System.out.println("Error when creating directory");
			return false;
		}
		System.out.println("Created path");
		return true;
	}
}
