package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TunaRunner implements CommandLineRunner {

    @Value("${tuna.port}")
    private int tunaPort;

    @Value("${tuna.path}")
    private String tunaPath;

    @Override
    public void run(String... args) throws Exception {
        runTuna();
    }

    private void runTuna() {
        ProcessBuilder processBuilder = new ProcessBuilder(tunaPath, "http", String.valueOf(tunaPort));
        try {
            Process process = processBuilder.start();
            new Thread(() -> {
                try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.err.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}