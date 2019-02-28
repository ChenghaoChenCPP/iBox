package iBoxCpp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EventWatcher {
	
	private String directory;
	private Path path;

	public EventWatcher() {
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Path getPath() {
		return path;
	}

	public boolean checkValidDriectory() {
		if(directory.equals("")) {
			System.out.println("Path is empty");
			return false;
		}
		
		path = Paths.get(directory);
		
		if(!Files.exists(path))
			return createDirectory(path);
		
		return true;
	}
	
	private boolean createDirectory(Path path) {
		boolean created = new File(directory).mkdirs();
		if(!created) {
			System.out.println("Error occured while creating directory");
			return false;
		}
		System.out.println("Created path");
		return true;
	}
}
