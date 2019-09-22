package com.dsa.salesprocessor.watcher;

import com.dsa.salesprocessor.processor.FileProcessor;
import com.dsa.salesprocessor.util.ProcessedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class WatcherService {

    public final static String BASE_DIRECTORY = System.getProperty("user.home") + File.separator + "data";
    public final static String INPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "in";
    public final static String OUTPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "out";

    @Autowired
    FileProcessor fileProcessor;

    public void watch() {

        try {

            WatchService watchService = FileSystems.getDefault().newWatchService();

            System.out.println("Input: " + INPUT_DIRECTORY);
            Path path = Paths.get(INPUT_DIRECTORY);

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    List<ProcessedFile> processedFiles = new ArrayList<>();
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                    fileProcessor.processor(Files.list(path));
                }
                key.reset();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
