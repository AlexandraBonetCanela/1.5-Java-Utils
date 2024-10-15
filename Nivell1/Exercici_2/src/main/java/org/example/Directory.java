package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Directory {

    public void inspectTreeDirectoryContent(String directoryPath) throws InvalidPathException, NotDirectoryException, IOException {

        Path directory = Paths.get(directoryPath);

        if (!Files.isDirectory(directory)) {
            throw new NotDirectoryException("Not a directory");
        }

        listDirectoryContent(directory);
    }

    public void listDirectoryContent(Path dir) throws IOException {
        listDirectoryContent(dir,0);
    }

    public void listDirectoryContent(Path pathway, int hierarchyPosition) throws IOException{

        ArrayList<Path> files = new ArrayList<>();

        String marker = "";
        for (int i = 0; i < hierarchyPosition; i++) {
            marker = marker + "->";
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(pathway)) {
            for (Path file: stream) {
                files.add(file);
            }
            files.sort(Comparator.comparing(path -> path.getFileName().toString()));
            char type = ' ';
            for (Path file: files) {
                if (Files.isDirectory(file)) {
                    type = 'D';
                    Path childPath = pathway.resolve(file.getFileName().toString());
                    int newHierarchyPosition = hierarchyPosition + 1;
                    System.out.println(marker + file.getFileName() + " " + type + " " + Files.getLastModifiedTime(file));
                    listDirectoryContent(childPath, newHierarchyPosition);
                } else if (Files.isRegularFile(file)) {
                    type = 'F';
                    System.out.println(marker + file.getFileName() + " " + type + " " + Files.getLastModifiedTime(file));
                }
            }
        }
    }
}
