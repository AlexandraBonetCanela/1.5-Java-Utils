package org.example;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        readFile();
    }

    //EXERCICI 4
    public static void readFile() {
        System.out.println("Enter .txt file directory's path: ");
        String path = scanner.nextLine();
        Path filePath = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}