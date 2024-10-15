package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            readFile(args[0]);
        } catch (IOException | InvalidPathException e) {
            log.error("Error reading file");
        }
    }

    public static void readFile(String path) throws InvalidPathException,IOException {
        System.out.println("Enter .txt file directory's path: ");
        Path filePath = Paths.get(path);
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
    }
}