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

    Path resultPath;
    public Directory(){}


    //EXERCICI 2 & 3
    public void inspectTreeDirectoryContent(Scanner scanner){
        Path path = askDirectoryPath(scanner);
        resultPath = Paths.get("src/main/resources/directoryResults.txt");
        listDirectoryContent(path);
    }

    public Path askDirectoryPath(Scanner scanner){
        System.out.println("Enter Directory's path: ");
        return Paths.get(scanner.nextLine());
    }

    public void listDirectoryContent(Path dir){
        listDirectoryContent(dir,0);
    }

    public void listDirectoryContent(Path pathway, int hierarchyPosition){

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
        } catch (IOException x) {
            System.err.println("File could not be opened.");
        }
    }
}
