package org.example;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        Teacher scienceTeacher = new Teacher(1, "Joaquin Miralles", "Joaquin@uoc.edu", "6335646544");
        serialize(scienceTeacher, "src/main/resources/scienceTeacher.ser");
        deserialize("src/main/resources/scienceTeacher.ser");
        scanner.close();
    }

    //EXERCICI 5.1
    public static void serialize(Object object, String fileName){
        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e){
            e.printStackTrace();

        }
    }
    //EXERCICI 5.2
    public static Object deserialize(String fileName){
        Object object = null;
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            object = ois.readObject();
            System.out.println("Object deserialized successfully: ");
            System.out.println(object);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return object;
    }
}