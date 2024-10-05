package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Directory {

    public Directory(){}

    // EXERCICI 1
    public void askDirectoryPath(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Directory's path: ");
        Path dir = Paths.get(scanner.next());
        listDirectoryContent(dir);
    }

    public void listDirectoryContent(Path dir){
        listDirectoryContent(dir, 0);
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
            Character type = ' ';
            for (Path file: files) {
                if (Files.isDirectory(file)) {
                    type = 'D';
                    Path childPath = pathway.resolve(file.getFileName().toString());
                    int newHierarchyPosition = hierarchyPosition + 1;
                    System.out.println(marker + file.getFileName() + " " + type);
                    listDirectoryContent(childPath, newHierarchyPosition);
                } else if (Files.isRegularFile(file)) {
                    type = 'F';
                    System.out.println(marker + file.getFileName() + " " + type);
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }


}
