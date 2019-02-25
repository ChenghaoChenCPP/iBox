package iBoxCpp;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

public class EventWatcherTest {
	
	EventWatcher e;
	
	@Before
	public void init() {
		e = new EventWatcher();
	}
	
	@Test
	public void checkValidDriectoryWithEmptyDirectory() {
		e.setDirectory("");
		assertFalse(e.checkValidDriectory());
	}
	
	@Test
	public void checkValidDirectoryWithDirectory() {
		e.setDirectory("./ibox");
		assertTrue(e.checkValidDriectory());
	}
	
	@Test
	public void checkValidDirectoryWithNoWritePermission() {
		e.setDirectory("./NoWritePermission/ibox");
		assertFalse(e.checkValidDriectory());
	}
	
	@Test
	public void checkValidDirectoryWithCreateDirectory() {
		e.setDirectory("./ibox/"+(int)(Math.random()*1000));
		assertTrue(e.checkValidDriectory());
	}
	
	
	
}
