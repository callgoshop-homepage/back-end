package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;

@SpringBootApplication
@EnableJpaAuditing
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class ,args);
    }

    @Override
    public void run(String... args) throws Exception {
        startSvelteKitServer();
    }

    private void startSvelteKitServer() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("node", "src/main/resources/sveltekit/build/index.js");
        processBuilder.directory(new File(System.getProperty("user.dir")));
        processBuilder.inheritIO();
        processBuilder.start();
    }
}