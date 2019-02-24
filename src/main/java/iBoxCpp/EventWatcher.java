package iBoxCpp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EventWatcher {
	String directory;
	Path path;
	public EventWatcher(){
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public boolean checkValidDriectory() {
		if(directory.equals("")) {
			System.err.println("Path is empty");
			return false;
		}
		
		path = Paths.get(directory);
		
		if(!Files.exists(path))
			return createDirectory(path);
		
		System.out.println("Path Exist");
		return true;
	}
	
	private boolean createDirectory(Path path) {
		boolean created = new File(directory).mkdirs();
		if(!created) {
			System.err.println("Error occured while creating directory");
			return false;
		}
		System.out.println("Create path");
		return true;
	}
}
