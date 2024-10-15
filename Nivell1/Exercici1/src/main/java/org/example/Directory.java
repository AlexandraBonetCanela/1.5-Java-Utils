package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Directory {

    public Directory(){}

    //EXERCICI 1
    public void inspectDirectory(Scanner scanner){
        Path directoryPath = askDirectoryPath(scanner);

        ArrayList<Path> files = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path file : stream) {
                files.add(file);
            }
            files.sort(Comparator.comparing(path -> path.getFileName().toString()));
            for (Path file : files) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Path askDirectoryPath(Scanner scanner){
        System.out.println("Enter Directory's path: ");
        return Paths.get(scanner.nextLine());
    }
}
