package org.example;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Directory {

    public void inspectDirectory(String directoryPath) {

        ArrayList<Path> files = new ArrayList<>();
        Path directory = Paths.get(directoryPath);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
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
}
