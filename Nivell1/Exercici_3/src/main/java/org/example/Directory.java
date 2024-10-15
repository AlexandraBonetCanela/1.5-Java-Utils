package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Directory {

    private static final Logger logger = LoggerFactory.getLogger(Directory.class);

    private final String outputFile = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "directoryResults.txt";
    Path resultPath;

    public void inspectTreeDirectoryContent(String directoryPath) throws InvalidPathException, NotDirectoryException, IOException {

        Path path = Paths.get(directoryPath);

        if (!Files.isDirectory(path)) {
            throw new NotDirectoryException("Not a path");
        }

        resultPath = Paths.get(outputFile);

        try (BufferedWriter writer = Files.newBufferedWriter(resultPath, StandardCharsets.UTF_8)){
            listDirectoryContent(path, writer);
        } catch (IOException x) {
            logger.error("File could not be opened.");
            throw x;
        }
        System.out.println("Directory content in: " + resultPath.toAbsolutePath());
    }

    public void listDirectoryContent(Path dir, BufferedWriter writer) throws IOException {
        listDirectoryContent(dir, writer, 0);
    }

    public void listDirectoryContent(Path pathway, BufferedWriter writer, int hierarchyPosition) throws IOException{

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
        }
    }
}
