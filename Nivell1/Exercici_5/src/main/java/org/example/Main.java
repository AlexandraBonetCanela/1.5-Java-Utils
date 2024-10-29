package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Teacher scienceTeacher = new Teacher(1, "Joaquin Miralles", "Joaquin@uoc.edu", "6335646544");
        try {
            serialize(scienceTeacher, "src/main/resources/scienceTeacher.ser");
        } catch (IOException e) {
            log.error("Failed to serialize scienceTeacher");
        }

        try {
            deserializeTeacher("src/main/resources/scienceTeacher.ser");
        } catch (ClassNotFoundException e) {
            log.error("Could not find the correct class to deserialize");
        } catch (IOException e) {
            log.error("There was a problem while trying to read the file");
        }

    }

    public static void serialize(Object object, String fileName) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        }
    }

    public static void deserializeTeacher(String fileName) throws IOException, ClassNotFoundException {
        Teacher deserializedObject = null;
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            deserializedObject = (Teacher) ois.readObject();
            System.out.println("Object deserialized successfully: ");
            System.out.println(deserializedObject);
            System.out.println(deserializedObject.getId());
            System.out.println(deserializedObject.getName());
            System.out.println(deserializedObject.getEmail());
            System.out.println(deserializedObject.getPhone());
        }
    }
}