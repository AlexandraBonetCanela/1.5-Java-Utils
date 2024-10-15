package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Directory {

    private static final Logger logger = LoggerFactory.getLogger(Directory.class);
    Path resultPath;
    public Directory(){}

    public void inspectTreeDirectoryContent(Scanner scanner){
        Path path = askDirectoryPath(scanner);
        resultPath = Paths.get("src/main/resources/directoryResults.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(resultPath, StandardCharsets.UTF_8)){
            listDirectoryContent(path, writer);
        } catch (IOException x) {
            logger.error("File could not be opened.");
        }
        System.out.println("Directory content in: " + resultPath.toAbsolutePath());
    }

    public Path askDirectoryPath(Scanner scanner){
        System.out.println("Enter Directory's path: ");
        return Paths.get(scanner.nextLine());
    }

    public void listDirectoryContent(Path dir, BufferedWriter writer){
        listDirectoryContent(dir, writer,0);
    }

    public void listDirectoryContent(Path pathway, BufferedWriter writer, int hierarchyPosition){

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
                    writer.write(marker + file.getFileName() + " " + type + " " + Files.getLastModifiedTime(file) + "\n");
                    listDirectoryContent(childPath, writer, newHierarchyPosition);
                } else if (Files.isRegularFile(file)) {
                    type = 'F';
                    writer.write(marker + file.getFileName() + " " + type + " " + Files.getLastModifiedTime(file) + "\n");
                }
            }
        } catch (IOException x) {
            logger.error("Could not list directory content.");
        }
    }
}
