package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NotDirectoryException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Directory directory = new Directory();

        try {
            directory.inspectDirectory(args[0]);
        } catch (InvalidPathException e) {
            log.error("The provided path is invalid");
        } catch (NotDirectoryException e) {
            log.error("The provided path is not a directory");
        } catch (IOException e) {
            log.error("An error occurred while reading the provided path");
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("An argument is required. Please use ./gradlew run --args=\"YOUR_DIRECTORY\"");
        }
    }
}