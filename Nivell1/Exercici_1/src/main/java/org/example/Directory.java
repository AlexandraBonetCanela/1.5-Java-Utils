package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Directory {

    public void inspectDirectory(String directoryPath) throws InvalidPathException, NotDirectoryException, IOException {

        ArrayList<Path> files = new ArrayList<>();

        Path directory = Paths.get(directoryPath);

        if (!Files.isDirectory(directory)) {
            throw new NotDirectoryException("Not a directory");
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                files.add(file);
            }
            files.sort(Comparator.comparing(path -> path.getFileName().toString()));
            for (Path file : files) {
                System.out.println(file.getFileName());
            }
        }
    }
}
