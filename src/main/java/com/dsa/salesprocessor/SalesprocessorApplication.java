package com.dsa.salesprocessor;

import com.dsa.salesprocessor.watcher.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

/**
 * Classe de inicialização do projeto.
 *
 * @author daniel.alves
 */
@SpringBootApplication
public class SalesprocessorApplication  implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(SalesprocessorApplication.class.getName());

    @Autowired
    WatcherService watcherService;

    public static void main(String[] args) {
        SpringApplication.run(SalesprocessorApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LOGGER.info("Iniciando sistema");
        watcherService.watch();
    }

}
