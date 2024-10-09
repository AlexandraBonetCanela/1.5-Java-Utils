package org.example;

import java.util.Scanner;

public class Main {

    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        Directory directory = new Directory();

        directory.inspectTreeDirectoryContent(scanner);
    }
}