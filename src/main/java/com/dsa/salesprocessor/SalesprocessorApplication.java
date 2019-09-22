package com.dsa.salesprocessor;

import com.dsa.salesprocessor.watcher.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesprocessorApplication  implements CommandLineRunner {

    @Autowired
    WatcherService watcherService;

    public static void main(String[] args) {
        SpringApplication.run(SalesprocessorApplication.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("Iniciando processamento");
        watcherService.watch();
    }

}
