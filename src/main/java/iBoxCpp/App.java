package iBoxCpp;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;


public class App {

	public static void main(String[] args) {
		//Please specify your directory
		String directory = "/Users/hoho/Desktop/CS5850/ibox";

		EventWatcher eventwatcher = new EventWatcher();
		S3Handler s3handler = new S3Handler();
		eventwatcher.setDirectory(directory);

		if (!eventwatcher.checkValidDriectory()) {
			return;
		}
		if (!s3handler.checkS3Info()) {
			return;
		}

		HashMap<WatchKey, Path> hm = new HashMap<WatchKey, Path>();

		try {
			WatchService service = FileSystems.getDefault().newWatchService();
			hm.put(eventwatcher.getPath().register(service, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY), eventwatcher.getPath());
			WatchKey watchKey;
			do {
				watchKey = service.take();
				Path eventDir = hm.get(watchKey);
				for (WatchEvent<?> event: watchKey.pollEvents()) {
					 WatchEvent.Kind<?> kind = event.kind();
					 Path eventPath = (Path) event.context();
					 s3handler.updateBucket(kind, eventDir, eventPath.toString(), directory);
				}

			} while (watchKey.reset());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
