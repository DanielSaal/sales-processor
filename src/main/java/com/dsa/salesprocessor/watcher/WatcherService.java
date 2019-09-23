package com.dsa.salesprocessor.watcher;

import com.dsa.salesprocessor.processor.FileProcessor;
import com.dsa.salesprocessor.util.ProcessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.logging.Logger;

/**
 * Serviço de monitoramento de arquivos.
 *
 * @author daniel.alves
 */
@Component
public class WatcherService {

    private static final Logger LOGGER = Logger.getLogger(WatcherService.class.getName());

    @Autowired
    FileProcessor fileProcessor;

    /**
     * Método de monitoramento.
     */
    public void watch() {

        try {
            LOGGER.info("Iniciando serviço de monitoramento na pasta " + ProcessorUtils.INPUT_DIRECTORY);
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get(ProcessorUtils.INPUT_DIRECTORY);

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    LOGGER.info("Evento "  + event.kind() + " disparado no arquivo " + event.context());
                    fileProcessor.processor(Files.list(path));
                }
                key.reset();
            }
        } catch (Exception e) {
            LOGGER.severe("Erro no serviço de monitoramento: " + e.getMessage());
        }
    }
}
